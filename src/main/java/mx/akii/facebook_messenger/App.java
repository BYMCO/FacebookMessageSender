package mx.akii.facebook_messenger;



public class App 
{
	public static void main( String[] args )
	{
		try {
			FacebookXMPPSender facebookXMPPSender = new FacebookXMPPSender(args[0], args[1]);
			facebookXMPPSender.sendMessage(args[2], args[3]);
			facebookXMPPSender.close();
		} catch (ConnectionException e) {
			e.printStackTrace();
			System.err.println("Fail: Connection");
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (SendException e) {
			e.printStackTrace();
			System.err.println("Fail: Send");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Message Send");
		System.exit(0);
	}

}
