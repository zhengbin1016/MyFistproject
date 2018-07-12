import java.util.Scanner;

public class ordering {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String [] names=new String[4];
		String [] dishMegs=new String[4];
		int [] times=new int [4];  //送餐时间  10~20之间
		String [] addresses=new String[4];  //送餐地址
		int states [] =new int[4];//订单状态，0：已预定  1：已完成
		double [] sumPrices=new double[4];//总金额
		
		// 初始化两条信息     不知道这个拿来干嘛用
		names[0]="张晴";
		dishMegs[0]="鱼香肉丝 1份";
		times[0]=12;
		addresses[0]="天成路68号";
		states[0]=0;
		sumPrices[0]=24.0;
		
		names[1]="张晴";
		dishMegs[1]="红烧带鱼 2份";
		times[1]=12;
		addresses[1]="天成路68号";
		states[1]=1;
		sumPrices[1]=76.0;

		
		//第二组数组，用于存储餐品信息！！
		String []dishNames={"红烧带鱼","鱼香肉丝","时令蔬菜"};
		double [] prices={38.0,18.0,10.0};
		int [] praiseNum=new int [3];//点赞数，因为初始值为0，所以直接定义3个初始值
		
		int num=-1;  
		do{
		System.out.println("*************************");
		System.out.println("1.我要订餐");
		System.out.println("2.查看餐袋");
		System.out.println("3.删除订单");
		System.out.println("4.签收订单");
		System.out.println("5.我要点赞");
		System.out.println("6.退出系统");
		System.out.println("*************************");
		System.out.print("请选择：");
		int choose=input.nextInt();
		
		boolean isAdd=false;  //true：找到空位置，false 没找到
		switch(choose){
		case 1:
			//订餐
			System.out.println("******我要订餐******");
			for(int i=0;i<names.length;i++){
				if(names[i]== null){
					isAdd=true;
					//显示餐品信息
					System.out.println("序号\t"+"餐品名\t"+"单价\t"+"点赞数");
					for(int j=0;j<dishNames.length;j++){
						String price=prices[j]+"元";
						String praise=praiseNum[j]+"赞";
						System.out.println((j+1)+"\t"+dishNames[j]+"\t"+price+"\t"+praise);
					}
					
					//用户输入信息
					System.out.print("请选择所定餐品序号：");
					int chooseDish=input.nextInt();
					System.out.print("请选择餐品份数：");
					int number=input.nextInt();
					String dishMeg =dishNames[chooseDish-1]+" "+number+"份";
					
					System.out.print("请输入订餐人姓名:");
					String name=input.next();
					
					//送餐时间
					System.out.print("请输入送餐时间(请输入10~20之间的整数)：");
					int time=input.nextInt();
					//如果输入错误，则从新输入
					while(time<10 && time>20){
						System.out.print("输入有误，请输入10~20之间的整数：");
						time=input.nextInt();
					}
					System.out.print("请输入送餐地址：");
					String adress=input.next();
					
					//计算餐费
					double sumPrice=prices[chooseDish-1]*number;
					double peiSong=sumPrice>50?0.0:6.0; 
					
					System.out.println("订餐成功");
					System.out.println("您定的是："+dishMeg);
					System.out.println("订餐人姓名："+name);
					System.out.println("送餐时间"+time+"点");
					System.out.println("送餐地址："+adress);
					System.out.println("餐费："+sumPrice+",送餐费："+peiSong);
					System.out.println("总金额："+(sumPrice+peiSong)+"元");
					
					//存储变量
					names[i]=name;
					dishMegs[i]=dishMeg;
					times[i]=time;
					addresses[i]=adress;
					sumPrices[i]=sumPrice+peiSong;
					
					
					
					
					break;
				}
				
			}
			//如果没有找到空位置，提示餐袋满了
			if(!isAdd){
				System.out.println("对不起，餐袋已满");
			}
			break;
		case 2:
			//查看餐袋
			System.out.println("******查看餐袋******");
			System.out.println("序号\t订餐人\t所定餐品\t送餐时间\t送餐地址\t\t总金额\t状态");
			for(int i=0;i<names.length;i++){
				if(names[i]!=null){
					String time=times[i]+"点";
					String sumPrice=sumPrices[i]+"元";
					String state=states[i]==0?"已预定":"已完成";
					System.out.println((i+1)+"\t"+names[i]+"\t"+dishNames[i]+"\t"+" "+time+"\t"+addresses[i]+"\t"+sumPrices[i]+"\t"+state);
						
				}
			}
			break;
		case 3:
			//删除订单
			System.out.println("******删除订单******");
			System.out.print("请输入要删除的订单号：");
			int delId=input.nextInt();
			int delIndex=-1;
			Boolean isIdfind=false;
			for(int i=0;i<=names.length;i++){
				if(names[i]!=null && states[i]==1 && delId==i+1){
					isIdfind=true;
					delIndex=i;
					break;
				}else if(names[i]!=null && states[i]==0 && delId==i+1){
					System.out.println("您选择的订单未签收，不能删除");
					isIdfind=true;
					break;
					
				}
				
			}
			if(!isIdfind){
				System.out.println("对不起，没有找到该订单");
			}
			if(delIndex!=-1){
				for(int i=delIndex+1;i<=names.length-1;i++){
					names[i-1]=names[i];
					dishMegs[i-1]=dishMegs[i];
					times[i-1]=times[i];
					addresses[i-1]=addresses[i];
					states[i-1]=states[i];
					// 循环位移
				}
				names[names.length-1]=null;
				dishMegs[names.length-1]=null;
				times[names.length-1]=0;
				addresses[names.length-1]=null;
				states[names.length-1]=0;
				// 最后一格清空
				
				System.out.println("订单删除成功");
			}
			break;
		case 4:
			//签收订单
			System.out.println("******签收订单******");
			System.out.print("请输入需要要签收的订单序号：");
			int id=input.nextInt();
			boolean isfind=false;
			for(int i=0;i<names.length;i++){
				if(names[i]!=null && states[i]==1 && id==i+1){//已完成状态  id==i+1，这个不理解
					System.out.println("该订单已完成不能再次签收");
					isfind=true;
					break;
				}else if(names[i]!=null && states[i]==0 && id==i+1){
					isfind=true;
					states[i] = 1;
					System.out.println("订单签收成功");
					break;
				}
				
			}
			if(!isfind){
				System.out.println("对不起，此订单不存在!");
			}
			break;
		case 5:
			//点赞
			System.out.println("******我要点赞******");
			System.out.println("序号\t"+"餐品名\t"+"单价\t"+"点赞数");
			for(int j=0;j<dishNames.length;j++){
				String price=prices[j]+"元";
				String praise=praiseNum[j]+"赞";
				System.out.println((j+1)+"\t"+dishNames[j]+"\t"+price+"\t"+praise);
			}
			System.out.print("请输入要点赞的餐品序号：");
			int praiseId=input.nextInt();
			praiseNum[praiseId-1]++;
			System.out.println("点赞成功");
			System.out.println(dishNames[praiseId-1]+" "+praiseNum[praiseId-1]+"赞");
			break;
		case 6:
			//退出系统
		default:
			break;
			
		
		}
		
		if(choose<1 || choose>5){
			break;
		}else{
		System.out.print("输入0返回：");
		num=input.nextInt();
		}
		}while(num==0);
		System.out.println("欢迎下次光临！");
	}

}
