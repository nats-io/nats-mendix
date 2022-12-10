package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(mendixsso.actions.CalculateOpenIDFromUUID.class);
    registrator.registerUserAction(mendixsso.actions.DecryptString.class);
    registrator.registerUserAction(mendixsso.actions.DeleteExpiredAuthRequests.class);
    registrator.registerUserAction(mendixsso.actions.DeleteExpiredTokens.class);
    registrator.registerUserAction(mendixsso.actions.EncryptString.class);
    registrator.registerUserAction(mendixsso.actions.ExtractUUIDFromOpenID.class);
    registrator.registerUserAction(mendixsso.actions.FindOrCreateUserWithUserInfo.class);
    registrator.registerUserAction(mendixsso.actions.GenerateRandomPassword.class);
    registrator.registerUserAction(mendixsso.actions.GetTokenEndpointURI.class);
    registrator.registerUserAction(mendixsso.actions.GetUserInfoEndpointURI.class);
    registrator.registerUserAction(mendixsso.actions.GetUserProfileFromUserInfoJSON.class);
    registrator.registerUserAction(mendixsso.actions.InitializeUserMapper.class);
    registrator.registerUserAction(mendixsso.actions.LoadBooleanValueFromEnvOrDefault.class);
    registrator.registerUserAction(mendixsso.actions.LoadStringValueFromEnvOrDefault.class);
    registrator.registerUserAction(mendixsso.actions.LogOutUser.class);
    registrator.registerUserAction(mendixsso.actions.StartSignOnServlet.class);
    registrator.registerUserAction(natsconnectormodule.actions.ActionCloseConnection.class);
    registrator.registerUserAction(natsconnectormodule.actions.ActionConnect.class);
    registrator.registerUserAction(natsconnectormodule.actions.ActionManageGetSubjects.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
