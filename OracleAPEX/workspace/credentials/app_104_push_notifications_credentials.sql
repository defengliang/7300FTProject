prompt --workspace/credentials/app_104_push_notifications_credentials
begin
--   Manifest
--     CREDENTIAL: App 104 Push Notifications Credentials
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_imp_workspace.create_credential(
 p_id=>wwv_flow_imp.id(9854922518446079)
,p_name=>'App 104 Push Notifications Credentials'
,p_static_id=>'App_104_Push_Notifications_Credentials'
,p_authentication_type=>'KEY_PAIR'
,p_prompt_on_install=>false
);
wwv_flow_imp.component_end;
end;
/
