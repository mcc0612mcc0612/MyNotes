
#include <iostream>
using namespace std;
//全局变量定义
int Available[100]; //可利用资源向量Available ,是个含有m个元素的数组，其中的每一个元素代表一类可利用的资源数目。如果Available[j] = K，则表示系统中现有Rj类资源K个。
int Max[50][100];   //最大需求矩阵Max 　　这是一个n×m的矩阵，它定义了系统中n个进程中的每一个进程对m类资源的最大需求。如果Max[i, j] = K，则表示进程i需要Rj类资源的最大数目为K。
int Allocation[50][100];  //已经分配矩阵
int Need[50][100];        //进程需求矩阵
int Request[50][100];     //M个进程还需要N类资源的资源量
int Finish[50];//
int p[50];
int m, n;   //M个进程,N类资源

//安全性算法3
int Safe()
{
	int i, j, l = 0;
	int Work[100]; //可提供给进程各类资源资源数组
	for (i = 0; i < n; i++)
		Work[i] = Available[i];//在执行安全算法开始时，可提供的各类资源数目=系统现有各类资源数目;Work=Available; 
	for (i = 0; i < m; i++)
		Finish[i] = 0;//表示系统是否有足够的资源分配给进程
	for (i = 0; i < m; i++)
	{
		if (Finish[i] == 1)//工作向量等于1,进程执行
			continue;
		else
		{
			for (j = 0; j < n; j++)
			{
				if (Need[i][j] > Work[j])
					break;
			}
			if (j == n)
			{
				Finish[i] = 1;
				for (int k = 0; k < n; k++)
					Work[k] = Work[k] + Allocation[i][k];//直至完成，并释放出分配给它的资源，故应执行可提供资源数量更新
				p[l++] = i;
				i = -1;
			}
			else continue;
		}
		if (l == m)
		{
			cout << "系统是安全的" << '\n';
			cout << "系统安全序列是:\n";
			for (i = 0; i < l; i++)
			{
				cout << p[i];
				if (i != l - 1)
					cout << "-->";
			}
			cout << '\n';
			return 1;
		}
	}
	return 0;
}

//银行家算法
int main()
{
	int i, j, mi;
	cout << "输入进程的数目:\n";
	cin >> m;
	cout << "输入资源的种类:\n";
	cin >> n;
	cout << "输入每个进程最多所需的各类资源数,按照" << m << "x" << n << "矩阵输入\n";
	for (i = 0; i < m; i++)
		for (j = 0; j < n; j++)
			cin >> Max[i][j];//将用户输入的资源需求放进最大需求矩阵数组里

	cout << "输入每个进程已经分配的各类资源数,按照" << m << "x" << n << "矩阵输入\n";
	for (i = 0; i < m; i++)
	{
		for (j = 0; j < n; j++)
		{
			cin >> Allocation[i][j];//分配矩阵

			Need[i][j] = Max[i][j] - Allocation[i][j];//进程需要的各类资源数=进程最大需求资源数-已分配资源数

			if (Need[i][j] < 0)
			{
				cout << "你输入的第" << i + 1 << "个进程所拥有的第" << j + 1 << "个资源错误，请重新输入:\n";
				j--;
				continue;
			}
		}
	}
	cout << "请输入各个资源现有的数目:\n";
	for (i = 0; i < n; i++)
		cin >> Available[i];
	Safe();
	while (1)
	{
		cout << "输入要申请的资源的进程号：（第一个进程号为0，第二个进程号为1，依此类推）\n";
		cin >> mi;
		cout << "输入进程所请求的各个资源的数量\n";
		for (i = 0; i < n; i++)
			cin >> Request[mi][i];
		for (i = 0; i < n; i++)
		{

			if (Request[mi][i] > Need[mi][i])
			{
				cout << "所请求资源数超过进程的需求量！\n";
				return 0;
			}
			if (Request[mi][i] > Available[i])
			{
				cout << "所请求资源数超过系统所有的资源数！\n";
				return 0;
			}
		}
		for (i = 0; i < n; i++)
		{
			Available[i] = Available[i] - Request[mi][i];//可利用资源=可利用资源-进程需要

			Allocation[mi][i] = Allocation[mi][i] + Request[mi][i];//已分配资源=已分配资源+进程需要

			Need[mi][i] = Need[mi][i] - Request[mi][i];//进程还需资源=进程还需资源-进程需要
		}
		if (Safe())
			cout << "同意分配请求\n";
		else
		{
			cout << "对不起.你的请求被拒绝…\n";
			for (i = 0; i < n; i++)
			{
				Available[i] = Available[i] - Request[mi][i];//可利用资源=可利用资源-进程需要

				Allocation[mi][i] = Allocation[mi][i] + Request[mi][i];//已分配资源=已分配资源+进程需要

				Need[mi][i] = Need[mi][i] - Request[mi][i];//进程还需资源=进程还需资源-进程需要
			}
		}
		for (i = 0; i < m; i++)
			Finish[i] = 0;
		
	}

	return 0;
}