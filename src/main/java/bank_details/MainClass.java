package bank_details;
import java.util.Scanner;


public class MainClass {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);  
		 
		 int ch;  
	        do {  
	        	Bank obj=new Bank();
	            System.out.println("\n ***Banking System Application***");  
	            System.out.println(" 1.Open new Account \n 2. Check Balance\n 3. Withdraw the amount \n 4. Deposit the amount\n 5.Show Details\n 6.Exit ");  
	            System.out.print("Enter your choice: ");  
	            ch = sc.nextInt();  
	            int temp;
	                switch (ch) {  
	                    case 1:  
	                    	try {
		                    	temp=obj.createAccount();
		                    	if(temp==-1){
		                    		throw new Exception("Account not opened....PAN card already used");
		                    	}
		                    	
		                    		System.out.println("Account opened Successfully\n Account number is : "+temp);
		                    	
	                    	}
	                    	catch(Exception e)
	                    	{
	                    		System.out.println(e);
	                    	}
	                        break;  
	                        
	                    case 2: 
	                        try {
	                        	temp = obj.checkBalance();
		                        if(temp==-1) {
		                        	throw new Exception("Account number not found");
		                        }
	                        	System.out.println("Available Balance is : "+temp);
	                        }
	                    	catch(Exception e)
	                    	{
	                    		System.out.println(e);
	                    	}
	                        
	                        break;  
	                        
	                    case 3:  
	                    	
	                        try {
	                        	temp = obj.withdrawal();
		                        if(temp==-1) {
		                        	throw new Exception("Account number not found");
		                        }
	                        	System.out.println("Available Balance is : "+temp);
	                        }
	                    	catch(Exception e)
	                    	{
	                    		System.out.println(e);
	                    	}
	                        break;
	                        
	                    case 4:  
	                    	try {
		                    	temp = obj.deposite();
		                        if(temp==-1) {
		                        	throw new Exception("Account number not found");
		                        }
	                        	System.out.println("Available Balance is : "+temp);
	                        }
	                    	catch(Exception e)
	                    	{
	                    		System.out.println(e);
	                    	}
	                        break;  
	                    case 5:
	                    	obj.showData();
	                    	break;
	                    	
	                    case 6:  
	                        System.out.println("See you soon...");  
	                        break;
	                        
	                }  
	            }while (ch != 6);
	        sc.close();
	}

}
