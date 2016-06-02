package guardSuspensionn;

public class TestThread {
	public static void main(String[] args) {
		RequestQueue requestQueue = new RequestQueue();
		for(int i=0;i<5;i++) new ServerThread(requestQueue, "ServerThread "+i).start();
		for(int i=0;i<5;i++) new ClientThread(requestQueue, "CliendThred "+i).start();
		System.out.println("hello");
	}
}
