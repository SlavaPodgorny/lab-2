public class Phone implements Runnable {
    private Coordinates position;

    public Phone(Coordinates position) {
        this.position = position;
    }

    public Coordinates getPosition(){
        return position;
    }

    public void makeCall(){
        Calls.CallsQueue.push(new Call(this));
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
        {
            makeCall();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
