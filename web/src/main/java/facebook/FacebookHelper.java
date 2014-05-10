package facebook;

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

		AccessToken accessToken = null;
		Configuration conf = generateConfiguration();
		OAuthSupport oAuthSupport = new OAuthAuthorization(conf);
		accessToken = oAuthSupport.getOAuthAppAccessToken();
		facebook.setOAuthAccessToken(accessToken);

		ResponseList<Friend> mutualFriends = facebook.friends().getMutualFriends("100008301374065", "100000023518026");

		System.out.println(mutualFriends);
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
}
