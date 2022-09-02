#include<iostream>
using namespace std;
#define maxsize 100
void CElevator(int array[],int m){
    
	int temp;
	int k=1;
	int now,l,r,d;
	int i,j,sum=0;
	for(i=0;i<m;i++)
		for(j=i+1;j<m;j++){
    
			if(array[i]>array[j]){
    
				temp=array[i];
				array[i]=array[j];
				array[j]=temp;
		}
}
		for(i=0;i<m;i++){
    
			cout<<array[i]<<" ";
		}
		cout<<"请输入当前的磁道号： ";
		cin>>now;
		if(array[m-1]<=now){
    
			for(i=m-1;i>=0;i--)
				cout<<array[i]<<" ";
			sum=array[m-1]-now;
		}
		else{
    
				while(array[k]<now){
    
					k++;
				}
				l=k-1;
				r=k;
				cout<<"请输入当前移动臂的移动方向（1表示向内，0表示向外）:";
				cin>>d;
				if(d==0){
    
					for(j=l;j>=0;j--){
    
						cout<<array[j]<<" ";
					}
					for(j=m-1;j>=k;j--){
    
						cout<<array[j]<<" ";
					}
					sum=now-2*array[0]+array[m-1];
				}
				else{
    
					for(j=r;j<m;j++){
    
						cout<<array[j]<<" ";
					}
					for(j=0;j<k;j++){
    
						cout<<array[j]<<" ";
					}
					sum=-now-array[0]+2*array[m-1];
				}
			}
			cout<<"移动的总道数："<<sum<<endl;
}
void main(){
    
	FILE *fp;
	int cidao[maxsize];
	int i=0,count;
	fp=fopen("cidao.txt","r+");
	if(fp==NULL){
    
		cout<<"文件打不开!"<<endl;
		exit(0);
	}
	while(!feof(fp)){
    
		fscanf(fp,"%d",&cidao[i]);
		i++;
	}
	count=i;
	for(i=0;i<count;i++){
    
		printf("%5d",cidao[i]);
	}
	cout<<endl;
	cout<<endl<<"系统的菜单如下："<<endl;
	printf("C-SCAN算法结果如下:");
	cout<<endl;
	CElevator(cidao,count);

}