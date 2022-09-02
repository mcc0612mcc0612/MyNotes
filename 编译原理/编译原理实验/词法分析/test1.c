/**左右注释
 * */
void main(){
	int num1 = 123;
	int num2 = 123;
	float num3 = 1234;
	double num4 = 12345;
	for(int i = 0;i < 100;++i){
		do{
			if(num1==num2||num3!=num4){
			    --num2;
				return 0;
			}else{
				return num1&=num2;
			}
		}while(num1&&num2)
	}
	//单行注释
	int d = num1<<5;
	int c = num2>>6;
	
	int a = num1+num2;
	int b = num1-num2;
	int c = num1*num2;
	int d = num1/num2;
	
	a+=b;
	a-=b;
	a*=b;
	a/=b;
	a|=b;
	a&=b;
}