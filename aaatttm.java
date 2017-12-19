import java.sql.*;
import java.util.Scanner;
public class Toppers 
{
	public static void main(String args[])
	{String url="jdbc:mysql://localhost:3306/";
	 String user="root";
	 String password="";
	 String query;
	 String userid,pass;
	 long bal,bal2=0,w,c;
	 int ch;
	 int th,fhu,hu,fi,f;
	 try
	 {   Scanner pin=new Scanner(System.in);
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 Connection con= DriverManager.getConnection(url,user,password);
		 Statement stt=con.createStatement();
		 stt.execute("CREATE DATABASE IF NOT EXISTS test");
		 stt.execute("USE test");
		 query="SELECT * FROM people WHERE name = 'ADMIN1'";
		 ResultSet res = stt.executeQuery(query);
		 if(res.next())
		 { bal2=res.getInt("bal");}
		  do
		    {System.out.println("Please SELECT your MODE!! ");
			 System.out.println("1. USER MODE");
			 System.out.println("2. MAINTENANCE MODE");
			 ch=pin.nextInt();
		   Toppers b=new Toppers();
		   Toppers m=new Toppers();
		    if(ch==1) 
	         {
		    	System.out.println("Enter your USER ID:");
		    	userid=pin.next();
		    	System.out.println("Enter your PASSWORD:");
		    	pass=pin.next();
		    	
		    	query="SELECT * FROM people WHERE name = '"+userid+"' AND pass = '"+pass+"'";
		    	res = stt.executeQuery(query);
	         	if(res.next())
		    	 {  bal=res.getInt("bal");
		    	  th=(int)(bal/1000);
                  fhu=(int)((bal%1000)/500);
                  hu=(int)(((bal%1000)%500)/100);
                  fi=(int)((((bal%1000)%500)%100)/50);
	         		System.out.println("_______WELCOME_______");
	         		System.out.println("\n______MENU______\n");
	         	      System.out.println("1.WITHDRAWAL..");
	         	      System.out.println("2.CREDIT MONEY..");
	         	      System.out.println("3.AVAILABLE BALANCE..\n");
	         	      System.out.print("\nEnter your choice --> ");
	         	      ch=pin.nextInt();
	         	      switch(ch)
	         	      {
	         	      	case 1:System.out.println("\nEnter amount to be withdrawn: ");
	         	                 w=pin.nextInt();
	         	                 boolean x=validat(w,bal,bal2);
	         	                 if(x==true)
	         	                  {bal=bal-w;
	         	                   System.out.println("\nTransaction Successful!!\n New Balance: "+bal);
	         	                   th=th-(int)(w/1000);
	         	                   fhu=fhu-(int)((w%1000)/500);
	         	                   hu=hu-(int)(((w%1000)%500)/100);
	         	                   fi=fi-(int)((((w%1000)%500)%100)/50);
	         	                   query="UPDATE people SET bal='"+bal+"' WHERE name = '"+userid+"'";
	         	                   stt.execute(query);
	         	                  }
	         	                 break;
	         	        case 2:System.out.println("\nEnter amount to be credited: ");
	         	                 c=pin.nextInt();
	         	                 boolean z=validat(c,1000000,1000000);
	         	                 if(z==true)
	         	                  {bal=bal+c;
	         	                   System.out.println("\nAmount credited Successfully!!\n New Balance: "+bal);
	         	                   th=th+(int)(c/1000);
	         	                   fhu=fhu+(int)((c%1000)/500);
	         	                   hu=hu+(int)(((c%1000)%500)/100);
	         	                   fi=fi+(int)((((c%1000)%500)%100)/50);
	         	                   query="UPDATE people SET bal='"+bal+"' WHERE name = '"+userid+"'";
	         	                   stt.execute(query);
	         	                  }
	         	                 break;
	         	        case 3:System.out.println("\n________BALANCE ENQUIRY________\n ");
	         	               System.out.println("\nAccount Balance: "+bal);
	         	               System.out.println("\nDenominations of 1000: "+th);
	         	               System.out.println("\nDenominations of 500: "+fhu);
	         	               System.out.println("\nDenominations of 100: "+hu);
	         	               System.out.println("\nDenominations of 50: "+fi);
	         	               break;
	         	        default:System.out.println("\nInvalid choice!! Please Try Again!!\n");
	         	                break;      
	         	      }
		    	 }
	         	else
	         	{
	         		System.out.println("INVALID USER!!");
	         	}
	         } 
	        else if(ch==2)
	         {
	        	System.out.println("Enter your MAINTENANCE ID:");
		    	userid=pin.next();
		    	System.out.println("Enter your PASSWORD:");
		    	pass=pin.next();
		    	
		    	query="SELECT * FROM people WHERE name = '"+userid+"' AND pass = '"+pass+"'";
		    	res = stt.executeQuery(query);
	         	if(res.next())
		    	 {
	         		 System.out.println("_______WELCOME_______");
	         		 System.out.println("SELECT MODE:");
	         		 System.out.println("1.CREDIT CASH");
	         		 System.out.println("2.ENQUIRY");
	         		 ch=pin.nextInt();
	         		try{
	         		 switch(ch)
	         		 {case 1: System.out.println("\n_________MAINTENANCE MODE__________\n (CREDIT ONLY)");
	         		            System.out.println("Enter number of notes for denomination 1000: ");
	         		            th=pin.nextInt();
	         		            System.out.println("Enter number of notes for denomination 500: ");
	         		            fhu=pin.nextInt();
	         		            System.out.println("Enter number of notes for denomination 100: ");
	         		            hu=pin.nextInt();
	         		            System.out.println("Enter number of notes for denomination 50: ");
	         		            fi=pin.nextInt();
	         		            System.out.println("");
	         		            bal2=bal2+((th*1000)+(fhu*500)+(hu*100)+(fi*50));
	         		            System.out.println("UPDATED BALANCE: "+bal2);
	         		            query="UPDATE people SET bal = '"+bal2+"' WHERE fname = 'ADMIN'";
         	                    stt.execute(query);
	         		            break;
	         		  case 2: query="SELECT * FROM people WHERE fname = 'ADMIN'";
	  		    	            res = stt.executeQuery(query);
	  		    	            if(res.next())
	  		    	            {bal2=res.getInt("bal");}
	  		    	            th=(int)(bal2/1000);
	  		                    fhu=(int)((bal2%1000)/500);
	  		                    hu=(int)(((bal2%1000)%500)/100);
	  		                    fi=(int)((((bal2%1000)%500)%100)/50);
	         			        System.out.println("\n________BALANCE ENQUIRY________\n ");
	         	                System.out.println("\nATM Balance: "+bal2);
	         	                System.out.println("\nDenominations of 1000: "+th);
	         	                System.out.println("\nDenominations of 500: "+fhu);
	         	                System.out.println("\nDenominations of 100: "+hu);
	         	                System.out.println("\nDenominations of 50: "+fi);
	         	                break;
	         	      } 
	         		 }
	         		 catch(Exception e)
	         		 {
	         			e.printStackTrace(); 
	         		 }
		    	 
		    	 }
	         	else
	         	{
	         		System.out.println("INVALID USER!!");
	         	}
	         }
	       System.out.println("Press 0 to continue or 1 to exit: ");
	         f=pin.nextInt();
	     }while(f==0);
		  res.close();
		  /*res2.close();
		  res3.close();
		  res4.close(); */
		  stt.close();
		  con.close();
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
}

 static boolean validat(long w,long bal,long bal2)
 { 
   if(bal==0 && bal>bal2)
    {
     System.out.println("Sorry no amount left!!");
     return false;
    }
    else if(w>bal) 
     {
         System.out.println("Your amount is larger than currently available amount in ATM");
	     return false;
	 }
	 else if(w%50!=0)
      {  System.out.println("Your amount must be at least in multiple of 50");
         	return false;
	  }
     else
      return true;
   }
 
}	



	
	
	
	
	


 




	