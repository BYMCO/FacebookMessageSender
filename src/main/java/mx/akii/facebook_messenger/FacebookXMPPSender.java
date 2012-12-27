package mx.akii.facebook_messenger;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class FacebookXMPPSender {
	
	private XMPPConnection mFbConnection;

	public FacebookXMPPSender(String key, String token)
			throws ConnectionException{

		mFbConnection = new XMPPConnection(
				new ConnectionConfiguration("chat.facebook.com", 5222){{
					setSASLAuthenticationEnabled(true);
				}});

		try {
			SASLAuthentication.registerSASLMechanism("X-FACEBOOK-PLATFORM", SASLXFacebookPlatformMechanism.class);
			SASLAuthentication.supportSASLMechanism("X-FACEBOOK-PLATFORM", 0);
			mFbConnection.connect();
			mFbConnection.login(key, token, "Application");   
		} catch (XMPPException e) {
			mFbConnection.disconnect();
			mFbConnection = null;
			throw new ConnectionException(e);
		}
	}
	
	public void
	sendMessage(String to, String message) 
			throws SendException{
		if (mFbConnection == null) throw new IllegalStateException("Connection to facebook is null");
		
		Chat chat = 
				mFbConnection.getChatManager().createChat(
						new StringBuilder("-").append(to).append("@chat.facebook.com").toString(), 
						new MessageListener(){
							@Override
							public void processMessage(Chat arg0, Message msg) {
								System.out.println("-----------------");
								System.out.println(msg);
								System.out.println("-----------------");
							}
						});

		try {
			chat.sendMessage(message);
		} catch (XMPPException e) {
			throw new SendException(e);
		}

	}
	
	public void
	close(){
		mFbConnection.disconnect();
		mFbConnection = null;
	}
}
