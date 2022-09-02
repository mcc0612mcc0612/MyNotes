#include <iostream>
#include<algorithm>
#include<queue>
using namespace std;
int visit[100] = { 0 };//��¼��������
int number;//��¼����������
int choose;//��¼ѡ����㷨
int position;//��¼��ǰ��ͷλ��
int  load[100] = { 0 };
int load1[100] = { 0 };
int load2[100] = { 0 };
int flag1[100] = { 0 };
void FCFS();
void SSTF();
void Le_Rifind(int low, int high, double sum, int position);
void SCAN();
void CSCAN();
void FSCAN();
void print() {
    cout << "*************" << endl;
    cout << "����1ѡ��FCFS������2ѡ��SSTF������3ѡ��CSCAN" << endl;
    cout << "��ѡ����̵����㷨:" << endl;
    cout << "*************" << endl;
}

int main() {
    cout << "��������̷�������������";
    cin >> number;
    cout << "���̷������У�";
    for (int i = 0; i < number; i++) {
        cin >> visit[i];

    }
    for (int i = 0; i < number; i++) {
        load[i] = visit[i];
    }
    sort(load, load + number);
    cout << "��ǰ��ͷλ�ã�";
    cin >> position;
    print();
    while (cin >> choose) {
        switch (choose) {
        case 1: FCFS();
            print();
            break;
        case 2:SSTF();
            print();
            break;  
        case 3:CSCAN();
            print();
            break;
        default:return 0;
        }
    }
}
void FCFS() {//�����ȷ���
    int flag = position;
    double sum = 0;
    int cnt;
    int length;
    queue<int> q;
    for (int i = 0; i < number; i++) {
        q.push(visit[i]);
    }
    cout << "FCFS�㷨" << endl;
    cout << "��һ�������ʵĴŵ�" << "        " << "���Ĵŵ���" << endl;
    for (int i = 0; i < number; i++) {
        cnt = q.front();
        q.pop();
        length = abs(flag - cnt);
        sum = sum + length;
        flag = cnt;
        cout << "    ";
        printf("%-22d", cnt);
        cout << length << endl;
    }
    cout << "�ܹ�Ѱ������:" << sum << endl;
    sum = sum / number;
    cout << "ƽ��Ѱ������:" << sum << endl;
}
void SSTF() {//���ʱ������
    cout << "SSTF�㷨" << endl;
    int low = -1, high = -1;

    cout << "��һ�������ʵĴŵ�" << "        " << "���Ĵŵ���" << endl;
    for (int i = 0; i < number; i++) {
        if (load[i] >= position) {
            high = i;
            low = high - 1;
            break;
        }
    }
    if (high == -1) {
        high = number;
        low = high - 1;

    }
    Le_Rifind(low, high, 0, position);

}
void Le_Rifind(int low, int high, double sum, int position) {
    int cnt, cnt1;
    if (low < 0 && high >= number) {
        cout << "�ܹ�Ѱ������:" << sum << endl;
        cout << "ƽ��Ѱ������:" << sum / number << endl;
        return;
    }
    else if (low >= 0 && high >= number) {
        cnt = abs(load[low] - position);
        sum += cnt;
        cout << "    ";
        printf("%-22d", load[low]);
        cout << cnt << endl;

        Le_Rifind(low - 1, high, sum, load[low]);
    }
    else if (low < 0 && high < number) {
        cnt = abs(load[high] - position);
        sum += cnt;
        cout << "    ";
        printf("%-22d", load[high]);
        cout << cnt << endl;

        Le_Rifind(low, high + 1, sum, load[high]);
    }
    else if (low >= 0 && high < number) {
        cnt1 = abs(load[high] - position);
        cnt = abs(load[low] - position);
        if (cnt > cnt1) {
            sum += cnt1;
            cout << "    ";
            printf("%-22d", load[high]);
            cout << cnt1 << endl;

            Le_Rifind(low, high + 1, sum, load[high]);
        }
        else {
            sum += cnt;
            cout << "    ";
            printf("%-22d", load[low]);
            cout << cnt << endl;

            Le_Rifind(low - 1, high, sum, load[low]);
        }
    }
}

void CSCAN() {
    cout << "CSCAN�㷨" << endl;
    double sum = 0;
    int cnt;
    int k = 0;
    int flag = position;
    int low = -1, high = -1;
    for (int i = 0; i < number; i++) {
        if (load[i] >= position) {
            high = i;
            low = high - 1;
            break;
        }
    }
    if (high == -1) {
        high = number;
        low = high - 1;
    }
    cout << "��һ�������ʵĴŵ�" << "        " << "���Ĵŵ���" << endl;
    for (int i = high; i < number; i++) {
        cnt = abs(load[i] - flag);
        sum += cnt;
        flag = load[i];
        cout << "    ";
        printf("%-22d", load[i]);
        cout << cnt << endl;
    }
    for (int i = flag; i < 200; i++) {
        if (flag1[i] == 0) {
            k++;
        }
    }
    for (int i = 0; i < load[0]; i++) {
        if (flag1[i] == 0) {
            k++;
        }
    }
    sum = sum + k + k;
    for (int i = 0; i <= low; i++) {
        cnt = abs(load[i] - flag);
        sum += cnt;
        flag = load[i];
        cout << "    ";
        printf("%-22d", load[i]);
        cout << cnt << endl;
    }
    cout << "�ܹ�Ѱ������:" << sum << endl;
    cout << "ƽ��Ѱ������:" << sum / number << endl;
}

