#include <iostream>
#include<algorithm>
#include<queue>
using namespace std;
int visit[100] = { 0 };//记录访问序列
int number;//记录访问序列数
int choose;//记录选择的算法
int position;//记录当前磁头位置
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
    cout << "输入1选择FCFS，输入2选择SSTF，输入3选择CSCAN" << endl;
    cout << "请选择磁盘调度算法:" << endl;
    cout << "*************" << endl;
}

int main() {
    cout << "请输入磁盘访问序列数量：";
    cin >> number;
    cout << "磁盘访问序列：";
    for (int i = 0; i < number; i++) {
        cin >> visit[i];

    }
    for (int i = 0; i < number; i++) {
        load[i] = visit[i];
    }
    sort(load, load + number);
    cout << "当前磁头位置：";
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
void FCFS() {//先来先服务
    int flag = position;
    double sum = 0;
    int cnt;
    int length;
    queue<int> q;
    for (int i = 0; i < number; i++) {
        q.push(visit[i]);
    }
    cout << "FCFS算法" << endl;
    cout << "下一个被访问的磁道" << "        " << "横跨的磁道数" << endl;
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
    cout << "总共寻道长度:" << sum << endl;
    sum = sum / number;
    cout << "平均寻道长度:" << sum << endl;
}
void SSTF() {//最短时间优先
    cout << "SSTF算法" << endl;
    int low = -1, high = -1;

    cout << "下一个被访问的磁道" << "        " << "横跨的磁道数" << endl;
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
        cout << "总共寻道长度:" << sum << endl;
        cout << "平均寻道长度:" << sum / number << endl;
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
    cout << "CSCAN算法" << endl;
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
    cout << "下一个被访问的磁道" << "        " << "横跨的磁道数" << endl;
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
    cout << "总共寻道长度:" << sum << endl;
    cout << "平均寻道长度:" << sum / number << endl;
}

