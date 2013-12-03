import java.util.concurrent.atomic.AtomicInteger;

public class Tower implements Runnable {
    private Coordinates position;
    private int distance;
    private AtomicInteger callsProcessed;
    private static Object sync = new Object();

    public Coordinates getPosition(){
        return position;
    }

    public int getDistance(){
        return distance;
    }

    public void processCall(){
        callsProcessed.incrementAndGet();
    }

    public Tower(Coordinates position, int distance){
        this.position = position;
        this.distance = distance;
        this.callsProcessed = new AtomicInteger(0);
    }

    @Override
    public void run() {

        while(!Calls.CallsQueue.isEmpty())
        {
            Call call;
            synchronized (sync)
            {
                call = Calls.CallsQueue.peek();
                if (position.distance(call.getCaller().getPosition()) < distance)
                {
                    processCall();
                    Calls.CallsQueue.remove(call);
                }
            }
        }
    }
}
