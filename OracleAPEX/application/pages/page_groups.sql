prompt --application/pages/page_groups
begin
--   Manifest
--     PAGE GROUPS: 104
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_flow_imp_page.create_page_group(
 p_id=>wwv_flow_imp.id(9649069612445794)
,p_group_name=>'Administration'
);
wwv_flow_imp_page.create_page_group(
 p_id=>wwv_flow_imp.id(9847731498446071)
,p_group_name=>'User Settings'
);
wwv_flow_imp.component_end;
end;
/
