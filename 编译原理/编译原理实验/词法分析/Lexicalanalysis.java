import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
/**�ʷ���������������������
 * ���Ҫ��
 * ��1�����������ʷ��ŵ������룻
 * ��2���ʷ���������Ӧ�ܷ������봮�еĴ���
 * ��3���ʷ�������Ϊ����һ���д���ʷ��������Ϊ��Ԫʽ������ɵ��м��ļ���
 * ��4��������������������������걸�������������Խ����
 * 
 * ���˵����
 * ��1�������Դ�Сд�����У�
 * ��2����ĸΪa-z A-Z������Ϊ0-9��
 * ��3�����Զ������ķ���������͸��죻
 * ��4����/*������Ϊ�����ע�Ͳ���,ע�ͷ��ż���ʶ��ע�����ݲ����з���ֱ�Ӻ�������
 * 
 * ʵ����Ŀ��
 * ����Ϊ�����ķ���������C�����Ӽ����ʷ��ŵ�ʾ�����벹�䵥�ʷ��ţ� 
 * ++��--�� >>,  <<, += , -= ,*=, /= ��&&���߼��룩��||���߼��򣩣������߼��ǣ��ȵȣ�
 * �������������C�����Ӽ����ʷ��ŵ������ķ�����Ʋ�ʵ����ʷ���������
 * 	<��ʶ��>����ĸ�� ��ĸ<��ʶ��>�� <��ʶ��>����
    <�޷�������>�����֦����� <�޷�������>
    <���ַ��ֽ��> ��; ��, ��(��) ��{��}
    //ÿ������������Լ������壬�͸�������һ����Ӧ����������
	//���ַ�
	<�Ӻ�>��+
	<����>��-
	<λ��>��&
	<λ��>��|  
	<����>��>
	<С��>��< 
	<��>��*
 	<б��>��/
 	<��̾��>��!
 	<��ֵ>��=
 	
 	//����ַ�
 	<���ڵ���>��><��ֵ>
 	<����>��><����>
 	<С�ڵ���>��<<��ֵ>
 	<����>��<<С��>
 	<������>��<<����>��!<��ֵ>
 	<����>��=<��ֵ>
 	<��ע��>��/<��>
 	<��ע��>��*<б��>
 	<����ע��>��/<б��>
 	<�ӵ���>��+<��ֵ>
 	<������>��-<��ֵ>
 	<�˵���>��*<��ֵ>
 	<������>��/<��ֵ>
 	<�����>��&<��ֵ>
 	<�����>��|<��ֵ>
 	<�Ӽ�>��+<�Ӻ�>
 	<����>��-<����>
 	<��>��&<λ��>
 	<��>��|<λ��>
 	
 	�����Եı����� ��void  int  float  double  if   else  for  do  while �ȵȣ�Ҳ�ɲ��䣬�����򲹳� main Ϊ�����֣���
 	һ����������һ�����
 * */

public class Lexicalanalysis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1������ʶ���ı��ķ��ű�
		SymbolTable st = SymbolTable.CreateSymbolTable();
		//2������ɨ����
		LexicalScanner sc = new LexicalScanner("test2.c");
		//3:�ʷ�����
		sc.LexicalAnalysisProcess();
		
	}

}




/**������������ɨ������
 * ˽�г�Ա������String taken ���� ��ʶ����ַ������ַ�
 * ˽�г�Ա������String value ���� ���浽���ű��ʶ��ɹ����ַ������ַ�
 * ˽�к��� lookupSymbolTableList() ��taken��ѯ�ַ��������ֱ�������Ӧ�Ĺؼ�������룬���򷵻�0
 * ˽�к��� outMidFile() ������̬�����Ԫʽ
 * �ʷ�������Ҫ����LexicalAnalysisProcess()
 * */
 class LexicalScanner {
	private String taken = null;
	private String value = null;
	private int categoryCode = 0;
	private FileReader fr = null;
	private FileWriter fw = null;
	private PushbackReader pr = null;
	private boolean midFileCreateSuccess = true;//�Ƿ��д����־������д����򲻴����м��ļ�
	private String outMidFileName = "";
	//���캯�����������ļ���
	public LexicalScanner(String fileName) {
		File fp = new File(fileName);
		
		//����ļ���׺���Ƿ�Ϸ�
		if(!fp.getName().endsWith(".c")) {
			System.out.println("�ļ���ʽ����ȷ...");
			return;
		}
		
		//�����ļ��Ķ���
		try {
			fr = new FileReader(fp);
			pr = new PushbackReader(fr,2); //�ÿ��Ի��˵Ļ�������װ�������������ַ���С�Ļ���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(fileName+"�ļ�������...");
			e.printStackTrace();
		}
		
		// ��������ļ�
		outMidFileName = fileName.substring(0, fileName.indexOf('.')) + ".tys";
		fp = new File(outMidFileName);

		try {
			if (!fp.exists()) {
				fp.createNewFile(); // ����������м��ļ�
			}
			fw = new FileWriter(fp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//�ʷ�����
	public void LexicalAnalysisProcess() {
		if(pr == null) {
			System.out.println("�ļ�����...");
			return;
		}
		
		char ch;
		int state;
		boolean keepScan = true;
		while(true) {
			boolean error = false;
			try {
				ch = (char) pr.read();
				if(ch==(char)-1) { //��ȡ�ļ�β��˵���ļ�������ɣ��˳�
					break;
				}
				
				switch(ch) {
				//���ַ��ֽ��
				case ';':case ',':case '(':case ')':case '{':case '}':
					taken = "���ַ��ֽ��";
					value = ""+ch;
					break;
				
				//+��
				case '+':
					ch = (char)pr.read();
					switch(ch) {
					case '+':
						taken = "�Ӽ�";
						value = "++";
						break;
					case '=':
						taken = "�ӵ���";
						value = "+=";
						break;
					default:
						taken = "�Ӻ�";
						value = "+";
						//ָ�����
						pr.unread(ch);
						break;
					}
					break;
					
				//-��
				case '-':
					ch = (char)pr.read();
					switch(ch) {
					case '-':
						taken = "����";
						value = "--";
						break;
					case '=':
						taken = "������";
						value = "-=";
						break;
					default:
						taken = "����";
						value = "-";
						pr.unread(ch);
						break;
					}
					break;
					
				//*��
				case '*':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "�˵���";
						value = "*=";
						break;
					case '/':
						taken = "��ע��";
						value = "*/";
						break;
					default:
						taken = "��";
						value = "*";
						pr.unread(ch);
						break;
					}
					break;
				
				///��
				case '/':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "������";
						value = "/=";
						break;
					case '*':
						taken = "��ע��";
						value = "/*";
						
						while(true)
						{
							ch = (char)pr.read();
							if(ch=='*')
							{
								ch = (char)pr.read();
								if(ch=='/')
								{
									char buf[]=new char[2];
									buf[0]='*';
									buf[1]='/';
									pr.unread(buf);
		
									break;
								}
							}
						}
						break;
					case '/':
						taken = "����ע��";
						value = "//";
						while(true)
						{
							ch = (char)pr.read();
							if(ch=='\n')
							{
								//����\n��ʾ��ʼ���س�������ȡ��һ��\n;
								pr.read();
								break;
							}
						}
						break;
					default:
						taken = "б��";
						value = "/";
						pr.unread(ch);
						break;
					}
					break;
					
				//&��
				case '&':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "�����";
						value = "&=";
						break;
					case '&':
						taken = "��";
						value = "&&";
						break;
					default:
						taken = "λ��";
						value = "&";
						pr.unread(ch);
						break;
					}
					break;
					
				//|��
				case '|':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "�����";
						value = "|=";
						break;
					case '|':
						taken = "��";
						value = "||";
						break;
					default:
						taken = "λ��";
						value = "|";
						pr.unread(ch);
						break;
					}
					break;
				//=��
				case '=':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "����";
						value = "==";
						break;
					default:
						taken = "��ֵ";
						value = "=";
						pr.unread(ch);
						break;
					
					}
					break;
				//!��
				case '!':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "������";
						value = "!=";
						break;
					default:
						taken = "��̾��";
						value = "!";
						pr.unread(ch);
						break;
					}
					break;
					
				//<��
				case '<':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "С�ڵ���";
						value = "<=";
						break;
					case '<':
						taken = "����";
						value = "<<";
						break;
					default:
						taken = "С��";
						value = "<";
						pr.unread(ch);
						break;
					}
					break;
					
				//>��
				case '>':
					ch = (char)pr.read();
					switch(ch) {
					case '=':
						taken = "���ڵ���";
						value = ">=";
						break;
					case '>':
						taken = "����";
						value = ">>";
						break;
					default:
						taken = "����";
						value = ">";
						pr.unread(ch);
						break;
					}
					break;
				//�ո�
				case ' ':case '\t':
					//����Ϊ
					taken = null;
					break;
				case '\n':
					//����\n��ʾ��ʼ���س�������ȡ��һ��\n;
					pr.read();
					taken = null;
					break;
				//��ʶ����������
				default:
					//�Գ�һ���ʷ���������
					taken = "";
					value = null;
					keepScan = true;
					state = 0;
					while (keepScan) { 
						switch(state) {
						case 0:
							if(!Character.isLetterOrDigit(ch)) {
								//����
								keepScan = false;
								break;
							}
							else if(ch == 'v')
								state = 1;
							else if(ch == 'i')
								state = 5;
							else if(ch == 'f')
								state = 9;
							else if(ch == 'd')
								state = 16;
							else if(ch == 'e')
								state = 22;
							else if(ch == 'w')
								state = 26;
							else if(ch == 'm')
								state = 31;
							else if(ch >= '0' && ch <= '9') 
								state = 35;
							else
								state = 36;
							break;
						case 1:
							if(ch == 'o') 
								state = 2;
							else{
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 2:
							if(ch == 'i')
								state = 3;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 3:
							if(ch == 'd') 
								state = 4;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 4:
							//�Ȳ�����ĸҲ�������֣�˵��ʶ�������
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 5:
							if(ch == 'f')
								state = 6;
							else if(ch == 'n')
								state = 7;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 6:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 7:
							if(ch == 't') 
								state = 8;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 8:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 9:
							if(ch == 'l')
								state = 10;
							else if(ch == 'o')
								state = 14;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 10:
							if(ch == 'o')
								state = 11;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 11:
							if(ch == 'a')
								state = 12;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 12:
							if(ch == 't')
								state = 11;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 13:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 14:
							if(ch == 'r')
								state = 15;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 15:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 16:
							if(ch == 'o')
								state = 17;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 17:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
								pr.unread(ch);
								ch = ' ';
							}
							else if(ch=='a'){
								state = 18;
							}else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 18:
							if(ch == 'b')
								state = 19;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 19:
							if(ch == 'c')
								state = 20;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 20:
							if(ch == 'e')
								state = 21;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 21:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 22:
							if(ch == 'l')
								state = 23;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 23:
							if(ch == 's')
								state = 24;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 24:
							if(ch == 'e')
								state = 25;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 25:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 26:
							if(ch == 'h')
								state = 27;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 27:
							if(ch == 'i')
								state = 28;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 28:
							if(ch == 'l')
								state = 29;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 29:
							if(ch == 'e')
								state = 30;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 30:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 31:
							if(ch == 'a')
								state = 32;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 32:
							if(ch == 'i')
								state = 33;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 33:
							if(ch == 'n')
								state = 34;
							else {
								state = 36;
								pr.unread(ch);
								ch = ' ';
							}
							break;
						case 34:
							if(!Character.isLetterOrDigit(ch)) {
								keepScan = false;
							}
							else {
								state = 36;
							}
							pr.unread(ch);
							ch = ' ';
							break;
						case 35:
							if(Character.isDigit(ch))
								state = 35;
							else if(Character.isLetter(ch)) {
								state = 35;
								error = true;
							}
							else {
								keepScan = false;
								pr.unread(ch);
								value = taken;
								if(error) {
									taken = "error";
								}else {
									taken = "�޷�������";
								}
								ch = ' ';
							}
							break;
						case 36:
							if(Character.isLetterOrDigit(ch))
								state = 36	;
							else {
								keepScan = false;
								pr.unread(ch);
								value = taken;
								taken = "��ʶ��";
								ch = ' ';
							}
							break;
						}
						if(ch!=' ')
							taken += ch;
						if(keepScan)
							ch = (char)pr.read(); //��ȡ��һ���ַ�
					}
					if(value==null)
						value = taken;
				break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(taken == null)
				continue;
			//������
			categoryCode = lookupSymbolTableList();
			outMidFile(); //���
			taken = null;
		}
		
		//�ر��ļ�
		try {
			fw.close();
			if(!midFileCreateSuccess) {
				System.out.println("�ʷ�����ʧ�ܣ���Ԫʽ�ļ�����ʧ��...");
			}
			else {
				System.out.println("�ʷ�������ɣ���Ԫʽ�ļ����ɳɹ�...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int lookupSymbolTableList() {
		if(taken == null) {
			return 0;
		}
		//��������
		return SymbolTable.getSymbolTableList().indexOf(taken)+1;
	}
	
	//�����Ԫʽ,���м��ļ�д���Ԫʽ
	private void outMidFile() {
		if(categoryCode==0) {
			System.out.println("Error:δ����ı�ʶ����"+value);
			this.midFileCreateSuccess = false;
			return;
		}
		System.out.println("["+categoryCode+","+value+"]");
		try {
			fw.write("["+categoryCode+","+value+"]"+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isCreateSuccess() {
		return midFileCreateSuccess;
	}
	
	public String getMidFileName() {
		return this.outMidFileName;
	}
}





 /**��ϴʷ������ķ��ű�
  * ����ȫֻ��һ�����ű����õ���ģʽ���
  * ��java�ļ����� list��ŷ���
  * */
class SymbolTable {
 	private static SymbolTable symbolTable; //����
 	private static List<String> symbolTableList = new ArrayList<String>();
 	
 	private SymbolTable(){}
     public static synchronized SymbolTable CreateSymbolTable(){
         if(symbolTable==null){
         	symbolTable = new SymbolTable();
         	//������ű�
         	symbolTableList.add("��ʶ��");
         	symbolTableList.add("�޷�������");
         	symbolTableList.add("���ַ��ֽ��");
         	symbolTableList.add("void");
         	symbolTableList.add("int");
         	symbolTableList.add("float");
         	symbolTableList.add("double");
         	symbolTableList.add("if");
         	symbolTableList.add("else");
         	symbolTableList.add("for");
         	symbolTableList.add("do");
         	symbolTableList.add("while");
         	symbolTableList.add("main");
         	symbolTableList.add("�Ӻ�");
         	symbolTableList.add("����");
         	symbolTableList.add("λ��");
         	symbolTableList.add("λ��");
         	symbolTableList.add("����");
         	symbolTableList.add("С��");
         	symbolTableList.add("��");
         	symbolTableList.add("б��");
         	symbolTableList.add("��̾��");
         	symbolTableList.add("��ֵ");
         	symbolTableList.add("���ڵ���");
         	symbolTableList.add("����");
         	symbolTableList.add("С�ڵ���");
         	symbolTableList.add("����");
         	symbolTableList.add("������");
         	symbolTableList.add("����");
         	symbolTableList.add("��ע��");
         	symbolTableList.add("��ע��");
         	symbolTableList.add("����ע��");
         	symbolTableList.add("�ӵ���");
         	symbolTableList.add("������");
         	symbolTableList.add("�˵���");
         	symbolTableList.add("������");
         	symbolTableList.add("�����");
         	symbolTableList.add("�����");
         	symbolTableList.add("�Ӽ�");
         	symbolTableList.add("����");
         	symbolTableList.add("��");
         	symbolTableList.add("��");
         	
         }
         return symbolTable;
     } 
     public static synchronized List<String> getSymbolTableList(){
 		return symbolTableList;
     }
 }