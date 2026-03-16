class A implements Runnable{
    synchronized public void run(){
        synchronized(A.class){
            for(int i=1;i<=20;i++){
                System.out.println(Thread.currentThread().getName()+" : "+i);

                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
            }
        }
    }
}
public class InterfaceThread{

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);

        t1.start();
        t2.start();
    }
}