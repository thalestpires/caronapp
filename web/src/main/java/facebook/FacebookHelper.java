package facebook;

import java.util.ArrayList;
import java.util.List;

import com.caronapp.model.Carona;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookHelper {

	public static void getFriends() throws FacebookException{
		Facebook facebook = new FacebookFactory().getInstance();

		facebook.setOAuthAppId("305609839591237", "0925b85fba792df463a03e099d0ed733");
		facebook.setOAuthPermissions("user_friends");

		AccessToken accessToken = new AccessToken("CAACEdEose0cBAFvoQcxCeKpmyjhynKOlCtmhSr24acJY8BoRTJItwk5mffZCtZCBCTyd5nS3tSHdN9JBDZCIflZBbhH53ZAmhW7ZAoaJIrDGzqeE24MZCYDHfpfobwXDSq6Bzma5kLUk6FF1gSZBc7Qa2fZCgm9jlIeMkQsixjDU8ZCzrEvkxHIR6h5smonfpNXwCKPN9mGmJY8AZDZD");
		Configuration conf = generateConfiguration();
		OAuthSupport oAuthSupport = new OAuthAuthorization(conf);
		//accessToken = oAuthSupport.getOAuthAppAccessToken();
		facebook.setOAuthAccessToken(accessToken);

		List<String> caronasDisponiveis = new ArrayList<String>();
		
		ResponseList<Friend> friends = facebook.getFriends("me");

		for (Friend friend : friends) {
			System.out.println(friend.getId());
		}
		ResponseList<Friend> mutualFriends = facebook.friends().getMutualFriends("me", "100000023518026");

	}

	private static Configuration generateConfiguration() {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();

		confBuilder.setDebugEnabled(true);
		confBuilder.setOAuthAppId("305609839591237");
		confBuilder.setOAuthAppSecret("0925b85fba792df463a03e099d0ed733");
		confBuilder.setUseSSL(true);
		confBuilder.setJSONStoreEnabled(true);
		Configuration configuration = confBuilder.build();
		return configuration;

	}

	public static List<Carona> getCaronasDisponiveis(String userID, List<Carona> caronas) throws FacebookException {
		/*Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("305609839591237", "0925b85fba792df463a03e099d0ed733");
		facebook.setOAuthPermissions("user_friends");

		AccessToken accessToken = new AccessToken("CAACEdEose0cBAFvoQcxCeKpmyjhynKOlCtmhSr24acJY8BoRTJItwk5mffZCtZCBCTyd5nS3tSHdN9JBDZCIflZBbhH53ZAmhW7ZAoaJIrDGzqeE24MZCYDHfpfobwXDSq6Bzma5kLUk6FF1gSZBc7Qa2fZCgm9jlIeMkQsixjDU8ZCzrEvkxHIR6h5smonfpNXwCKPN9mGmJY8AZDZD");
		facebook.setOAuthAccessToken(accessToken);
		
		List<Carona> result = new ArrayList<Carona>();
		
		for (Carona carona : caronas) {
			for (Carona carona : result) {
				
			}
		}*/
		
		//ResponseList<Friend> friends = facebook.getFriends("me");
		
		
		return null;
	}
}
