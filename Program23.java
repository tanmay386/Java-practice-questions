class SharedMessage {

    String message = null;

    synchronized void getMessage() {
        try {
            System.out.println("Waiting for message...");

            while (message == null) {
                wait();
            }

            System.out.println("Message received: " + message);

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    synchronized void setMessage() {
        message = "Hello Student";
        notify();
    }
}

class Program23 {
    public static void main(String[] args) {

        SharedMessage obj = new SharedMessage();

        Thread t1 = new Thread(() -> {
            obj.getMessage();
        });

        Thread t2 = new Thread(() -> {
            obj.setMessage();
        });

        t1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }

        t2.start();
    }
}