#CODSOFT TASK 2
#STUDENT GRADE CALCULATOR
import java.util.*;
class GradeCalculator
{
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
      
        System.out.println("Enter your name");
        String name=in.nextLine();
        System.out.println("Enter the no of  subjects:");
       int subjects=in.nextInt();
       int total=0;
       for(int i=1;i<=subjects;i++)
       
       {
           System.out.println("Enter  your "+ i + " subject mark: ");
    
           int marks=in.nextInt();
         if(marks>0 && marks<=100)
         {
           total+=marks;
         
       }
       else
       {
           System.out.println("Invlaid input!");
          
       }
       }
       int avg=(total)/subjects;

 
char Grade;
if(avg<50)
{
System.out.println("fail. No grade");
}
else if(avg>=50 && avg<60)
{
   Grade='D'; 

}
else if(avg>=60 && avg<70)
{
      Grade='C';

}
else if(avg>=70 && avg<80)
{
    Grade='B';

}
else if(avg>=80 && avg<90)
{
    Grade='A';

}
else if(avg>=90 && avg<100)
{
     Grade='O';
}


System.out.println("     **RESULT**     ");
System.out.println("NAME : "+name);
System.out.println("YOUR TOTAL MARKS : "+total);
System.out.println("YOUR AVERAGE PRCENTAGE : "+avg);
//System.out.println("YOUR GRADE : "+ Grade);

    }
}
