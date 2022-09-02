#include <stdio.h>

#include <stdlib.h>

#include <conio.h>

#define getpch(type) (type *)malloc(sizeof(type)) //Ϊ���̴���һ���ռ�

struct worktime
{

    float Tb; //��ҵ����ʱ��

    float Tc; //��ҵ���ʱ��

    float Ti; //��תʱ��

    float Wi; //��Ȩ��תʱ��
};

struct jcb
{

    char name[10]; //��ҵ��

    float subtime; //��ҵ����ʱ��

    float runtime; //��ҵ���������ʱ��

    char resource; //������Դ

    float Rp; //����ҵ��Ӧ��

    char state; //��ҵ״̬

    int worked_time; //������ʱ��

    struct worktime wt;

    int need_time; //Ҫ������ʱ��

    int flag; //���̽�����־

    struct jcb *link; //��ָ��

} *ready = NULL, *p;

typedef struct jcb JCB;

float T = 0;

int N;

JCB *front, *rear; //ʱ����ת������

void sort()

{

    JCB *first, *second;

    int insert = 0; //������

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

void creatJCB() //Ϊÿ����ҵ����һ��JCB����ʼ���γ�һ��ѭ��������

{

    JCB *p, *l;

    int i = 0;

    l = (JCB *)malloc(sizeof(JCB));

    printf("\n ��������ҵ�ĸ���:");

    scanf("%d", &N);

    printf("\n ��ҵ��No.%d:\n", i);

    printf("\n��������ҵ������:");

    scanf("%s", l->name);

    printf("\n��������ҵ��ʱ��:");

    scanf("%d", &l->need_time);

    l->state = 'r'; //��ҵ��ʼ״̬Ϊ����

    l->worked_time = 0;

    l->link = NULL;

    l->flag = 0;

    front = l;

    for (i = 1; i < N; i++)

    {

        p = (JCB *)malloc(sizeof(JCB));

        printf("\n ��ҵ��No.%d:\n", i);

        printf("\n��������ҵ������:");

        scanf("%s", p->name);

        printf("\n��������ҵ��ʱ��:");

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

void output() //�����������

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

int judge(JCB *p) //�ж����н������н���

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

void RRget() //ʱ��Ƭ��ת�㷨

{

    JCB *s;

    int flag1 = 0;

    s = (JCB *)malloc(sizeof(JCB));

    s = front;

    printf("\n--------------------------------------------\n");

    output();

    printf("����������һ������\n");

    getch(); //�����������

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

            printf("����������һ������...\n");

            getch();
        }

        if (s->state == 'e' && s->flag == 0)

        {

            printf("����%s�Ѿ�������ɣ�\n\n", s->name);

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

    printf("\n ��������ҵ�ĸ���:");

    scanf("%d", &num);

    for (i = 0; i < num; i++)

    {

        printf("\n ��ҵ��No.%d:\n", i);

        p = getpch(JCB);

        printf("\n ������ҵ��:");

        scanf("%s", p->name);

        printf("\n ������ҵ����ʱ��:");

        scanf("%f", &p->subtime);

        printf("\n ������ҵ����ʱ��:");

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
        printf("\n ��ҵ  ����ʱ��   ����ʱ��  ��Ӧ��  ����ʱ��  ���ʱ��  ��תʱ��   ��Ȩ��תʱ�� \n");

    else
        printf("\n ��ҵ  ����ʱ��   ����ʱ��  ����ʱ��  ���ʱ��  ��תʱ��   ��Ȩ��תʱ�� \n");

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

    printf("\n ��ҵ [%s] �����.\n", p->name);

    free(p);

    return (1);
}

void check(int select)

{

    JCB *jr;

    printf("\n **** ��ǰ�������е���ҵ��:%s", p->name);

    disp(p, select);

    jr = ready;

    printf("\n ****��ǰ��������״̬Ϊ:\n");

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

    printf("��ѡ����ҵ�����㷨�ķ�ʽ:\n");

    printf("\t1.FCFS 2.SJF 3.HRN 4.RR\n\n");

    printf("\t---*****************---\n");

    printf("��������ҵ�����㷨���(1-4):");

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

            printf("\n ִ�е�%d����ҵ \n", h);

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

            printf("\n ������һ������......");

            getchar();

            getchar();
        }

        printf("\n\n ��ҵ�Ѿ����.\n");

        printf("\t ������ҵ��ƽ����תʱ�䣺%.2f\n", sumTi / h);

        printf("\t ������ҵ�Ĵ�Ȩƽ����תʱ�䣺%.2f\n", sumWi / h);

        getchar();
    }
}