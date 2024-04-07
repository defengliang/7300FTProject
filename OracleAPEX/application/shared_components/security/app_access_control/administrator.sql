prompt --application/shared_components/security/app_access_control/administrator
begin
--   Manifest
--     ACL ROLE: Administrator
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_flow_imp_shared.create_acl_role(
 p_id=>wwv_flow_imp.id(9647055343445789)
,p_static_id=>'ADMINISTRATOR'
,p_name=>'Administrator'
,p_description=>'Role assigned to application administrators.'
);
wwv_flow_imp.component_end;
end;
/
