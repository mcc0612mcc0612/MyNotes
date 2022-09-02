#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdbool.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/wait.h>


union semuns
{
    int val;
    struct semid_ds *buf;
    unsigned short *array;
    struct seminfo *__buf;
};

#define ERR_EXIT(m) \
    do { \
        perror(m); \
        exit(EXIT_FAILURE); \
    } while(0)

//申请一个资源
int    wait_1fork(int no,int semid)
{
    //资源减1
    struct sembuf sb = {no,-1,0};
    int ret;
    ret = semop(semid,&sb,1);
    if(ret < 0) {
        ERR_EXIT("semop");
    }
    return ret;
}

// 释放一个资源
int free_1fork(int no,int semid)
{
    //资源加1
    struct sembuf sb = {no,1,0};
    int ret;
    ret = semop(semid,&sb,1);
    if(ret < 0) {
        ERR_EXIT("semop");
    }
    return ret;
}

//叉子是一个临界资源
#define DELAY (rand() % 5 + 1)

//相当于P操作
//第一个参数是叉子编号
//第二个参数是信号量编号
void wait_for_2fork(int no,int semid)
{
    //哲学家左边的叉子编号和哲学家编号是一样的
    int left = no;
    //右边的叉子
    int right = (no + 1) % 5;

    //叉子值是两个
    //操作的是两个信号量,即两种资源都满足,才进行操作
    struct sembuf buf[2] = {
        {left,-1,0},
        {right,-1,0}
    };
    //信号集中有5个信号量，只是对其中的资源sembuf进行操作
    semop(semid,buf,2);
}

//相当于V操作  ,释放叉子
void free_2fork(int no,int semid)
{
    int left = no;
    int right = (no + 1) % 5;
    struct sembuf buf[2] = {
        {left,1,0},
        {right,1,0}
    };
    semop(semid,buf,2);
}


//哲学家要做的事
void philosophere(int no,int semid)
{
    srand(getpid());
    for(;;)
    {
    #if 1
        //当两只叉子都可用的时候，哲学家才能进餐
        printf("the philosopher of %d is thinking\n",no);  //思考中
        sleep(DELAY);
        printf("the philosopher of %d is hungry\n",no);    //饥饿
        wait_for_2fork(no,semid); //拿到两只叉子才能吃饭
        printf("the philosopher of %d is eating\n",no);    //进餐
        sleep(DELAY);
        free_2fork(no,semid); //释放两只叉子
    #else
        //可能会造成死锁
        int left = no;
        int right = (no + 1) % 5;
        printf("the philosopher of %d is thinking\n",no);  //思考中
        sleep(DELAY);
        printf("the philosopher of %d is hungry\n",no);    //饥饿
        wait_1fork(left,semid);         //拿起左叉子（只要有一个资源就申请）
        sleep(DELAY);
        wait_1fork(right,semid);        //拿起右叉子
        printf("the philosopher of %d is eating\n",no);    //进餐
        sleep(DELAY);
        free_1fork(left,semid);         //释放左叉子
        free_1fork(right,semid);          //释放右叉子
    #endif
    }
}


int main(int argc,char *argv[])
{
    int semid;
    //创建信号量集，其中有5个信号量
    semid = semget(IPC_PRIVATE,5,IPC_CREAT | 0666);
    if(semid < 0) {
        ERR_EXIT("semid");
    }
    union semun su;
    su.val = 1;
    int i;
    for(i = 0;i < 5; ++i) {
        //第二个参数也是索引
        semctl(semid,i,SETVAL,su);
    }
    //创建4个子进程
    int num = 0;
    pid_t pid;
    for(i = 1;i < 5;++i)
    {
       pid = fork();
       if(pid < 0)
        {
           ERR_EXIT("fork");
        }
        if(0 == pid)  //子进程
        {
            num = i;
            break;
        }
    }
    //哲学家要做的事情
   philosophere(num,semid);
    return 0;
}