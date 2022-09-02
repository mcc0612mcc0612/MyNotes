#include <stdio.h>

#include <stdlib.h>

#include <conio.h>

#define getpch(type) (type *)malloc(sizeof(type)) //为进程创建一个空间

struct worktime
{

    float Tb; //作业运行时刻

    float Tc; //作业完成时刻

    float Ti; //周转时间

    float Wi; //带权周转时间
};

struct jcb
{

    char name[10]; //作业名

    float subtime; //作业到达时间

    float runtime; //作业所需的运行时间

    char resource; //所需资源

    float Rp; //后备作业响应比

    char state; //作业状态

    int worked_time; //已运行时间

    struct worktime wt;

    int need_time; //要求运行时间

    int flag; //进程结束标志

    struct jcb *link; //链指针

} *ready = NULL, *p;

typedef struct jcb JCB;

float T = 0;

int N;

JCB *front, *rear; //时间轮转法变量

void sort()

{

    JCB *first, *second;

    int insert = 0; //插入数

    if ((ready == NULL) || ((p->subtime) < (ready->subtime)))

    {

        p->link = ready;

        ready = p;

        T = p->subtime;

        p->Rp = 1;
    }

    else

    {

        first = ready;

        second = first->link;

        while (second != NULL)

        {

            if ((p->subtime) < (second->subtime))

            {

                p->link = second;

                first->link = p;

                second = NULL;

                insert = 1;
            }

            else

            {

                first = first->link;

                second = second->link;
            }
        }

        if (insert == 0)
            first->link = p;
    }
}

void SJFget()

{

    JCB *front, *mintime, *rear;

    int ipmove = 0;

    mintime = ready;

    rear = mintime->link;

    while (rear != NULL)

    {

        if ((rear != NULL) && (T >= rear->subtime) && (mintime->runtime) > (rear->runtime))

        {

            front = mintime;

            mintime = rear;

            rear = rear->link;

            ipmove = 1;
        }

        else

            rear = rear->link;
    }

    if (ipmove == 1)

    {

        front->link = mintime->link;

        mintime->link = ready;
    }

    ready = mintime;
}

void HRNget()

{

    JCB *front, *mintime, *rear;

    int ipmove = 0;

    mintime = ready;

    rear = mintime->link;

    while (rear != NULL)

        if ((rear != NULL) && (T >= rear->subtime) && (mintime->Rp) < (rear->Rp))

        {

            front = mintime;

            mintime = rear;

            rear = rear->link;

            ipmove = 1;
        }

        else

            rear = rear->link;

    if (ipmove == 1)
    {

        front->link = mintime->link;

        mintime->link = ready;
    }

    ready = mintime;
}

void creatJCB() //为每个作业创建一个JCB并初始化形成一个循环链队列

{

    JCB *p, *l;

    int i = 0;

    l = (JCB *)malloc(sizeof(JCB));

    printf("\n 请输入作业的个数:");

    scanf("%d", &N);

    printf("\n 作业号No.%d:\n", i);

    printf("\n请输入作业的名字:");

    scanf("%s", l->name);

    printf("\n请输入作业的时间:");

    scanf("%d", &l->need_time);

    l->state = 'r'; //作业初始状态为就绪

    l->worked_time = 0;

    l->link = NULL;

    l->flag = 0;

    front = l;

    for (i = 1; i < N; i++)

    {

        p = (JCB *)malloc(sizeof(JCB));

        printf("\n 作业号No.%d:\n", i);

        printf("\n请输入作业的名字:");

        scanf("%s", p->name);

        printf("\n请输入作业的时间:");

        scanf("%d", &p->need_time);

        p->state = 'r';

        p->worked_time = 0;

        p->flag = 0;

        l->link = p;

        l = l->link;
    }

    rear = l;
    rear->link = front;
}

void output() //进程输出函数

{

    int j;

    printf("name runtime needtime state\n");

    for (j = 1; j <= N; j++)

    {
        printf(" %-4s\t%-4d\t%-4d\t%-c\n", front->name, front->worked_time, front->need_time, front->state);

        front = front->link;
    }

    printf("\n");
}

int judge(JCB *p) //判断所有进程运行结束

{

    int flag = 1, i;

    for (i = 0; i < N; i++)

    {

        if (p->state != 'e')

        {

            flag = 0;

            break;
        }

        p = p->link;
    }

    return flag;
}

void RRget() //时间片轮转算法

{

    JCB *s;

    int flag1 = 0;

    s = (JCB *)malloc(sizeof(JCB));

    s = front;

    printf("\n--------------------------------------------\n");

    output();

    printf("请输入任意一键继续\n");

    getch(); //按任意键继续

    s = front;

    while (flag1 != 1)

    {

        if (s->state == 'r')

        {

            s->worked_time++;

            s->need_time--;

            if (s->need_time == 0)

                s->state = 'e';

            output();

            printf("请输入任意一键继续...\n");

            getch();
        }

        if (s->state == 'e' && s->flag == 0)

        {

            printf("进程%s已经运行完成！\n\n", s->name);

            s->flag = 1;
        }

        s = s->link;

        flag1 = judge(s);
    }

    printf("--------------------------------------------\n");
}

void input()

{

    int i, num;

    printf("\n 请输入作业的个数:");

    scanf("%d", &num);

    for (i = 0; i < num; i++)

    {

        printf("\n 作业号No.%d:\n", i);

        p = getpch(JCB);

        printf("\n 输入作业名:");

        scanf("%s", p->name);

        printf("\n 输入作业到达时刻:");

        scanf("%f", &p->subtime);

        printf("\n 输入作业运行时间:");

        scanf("%f", &p->runtime);

        printf("\n");

        p->state = 'w';

        p->link = NULL;

        sort();
    }
}

int space()

{

    int l = 0;
    JCB *jr = ready;

    while (jr != NULL)

    {

        l++;

        jr = jr->link;
    }

    return (l);
}

void disp(JCB *jr, int select)

{

    if (select == 3)
        printf("\n 作业  到达时间   服务时间  响应比  运行时刻  完成时刻  周转时间   带权周转时间 \n");

    else
        printf("\n 作业  到达时间   服务时间  运行时刻  完成时刻  周转时间   带权周转时间 \n");

    printf(" |%s\t", jr->name);

    printf(" |%.2f\t  ", jr->subtime);

    printf(" |%.2f\t", jr->runtime);

    if (select == 3 && p == jr)
        printf("|%.2f   ", jr->Rp);

    if (p == jr)
    {

        printf("|%.2f\t ", jr->wt.Tb);

        printf(" |%.2f  ", jr->wt.Tc);

        printf("  |%.2f\t", jr->wt.Ti);

        printf("  |%.2f", jr->wt.Wi);
    }

    printf("\n");
}

int destroy()

{

    printf("\n 作业 [%s] 已完成.\n", p->name);

    free(p);

    return (1);
}

void check(int select)

{

    JCB *jr;

    printf("\n **** 当前正在运行的作业是:%s", p->name);

    disp(p, select);

    jr = ready;

    printf("\n ****当前就绪队列状态为:\n");

    while (jr != NULL)

    {

        jr->Rp = (jr->runtime + T - jr->subtime) / jr->runtime;

        disp(jr, select);

        jr = jr->link;
    }

    destroy();
}

void running(JCB *jr)

{

    if (T >= jr->subtime)
        jr->wt.Tb = T;

    else
        jr->wt.Tb = jr->subtime;

    jr->wt.Tc = jr->wt.Tb + jr->runtime;

    jr->wt.Ti = jr->wt.Tc - jr->subtime;

    jr->wt.Wi = jr->wt.Ti / jr->runtime;

    T = jr->wt.Tc;
}

int main()

{

    int select = 0, len, h = 0;

    float sumTi = 0, sumWi = 0;

    printf("\t---*****************---\n");

    printf("请选择作业调度算法的方式:\n");

    printf("\t1.FCFS 2.SJF 3.HRN 4.RR\n\n");

    printf("\t---*****************---\n");

    printf("请输入作业调度算法序号(1-4):");

    scanf("%d", &select);

    if (select == 4)

    {
        creatJCB();

        RRget();
    }

    else

    {

        input();

        len = space();

        while ((len != 0) && (ready != NULL))

        {

            h++;

            printf("\n 执行第%d个作业 \n", h);

            p = ready;

            ready = p->link;

            p->link = NULL;

            p->state = 'R';

            running(p);

            sumTi += p->wt.Ti;

            sumWi += p->wt.Wi;

            check(select);

            if (select == 2 && h < len - 1)
                SJFget();

            if (select == 3 && h < len - 1)
                HRNget();

            printf("\n 按任意一键继续......");

            getchar();

            getchar();
        }

        printf("\n\n 作业已经完成.\n");

        printf("\t 此组作业的平均周转时间：%.2f\n", sumTi / h);

        printf("\t 此组作业的带权平均周转时间：%.2f\n", sumWi / h);

        getchar();
    }
}