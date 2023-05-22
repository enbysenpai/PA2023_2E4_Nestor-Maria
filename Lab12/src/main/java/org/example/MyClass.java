package org.example;

public class MyClass
{
   public void publicMethod()
   {
       System.out.println("This is a public method");
   }

   private void privateMethod()
   {
       System.out.println("This is a private method");
   }

   protected void protectedMethod()
   {
       System.out.println("This is a protected method");
   }

   public static void staticMethod()
   {
       System.out.println("This is a static method");
   }

   @Test
   public void testMethod()
   {
       System.out.println("This is an annotated method");
   }

   public void methodWithParameters(String parameter1,int parameter2,long parameter3)
   {
       System.out.println("The last method has parameters: "+parameter1+" "+parameter2+" "+parameter3);
   }
}
