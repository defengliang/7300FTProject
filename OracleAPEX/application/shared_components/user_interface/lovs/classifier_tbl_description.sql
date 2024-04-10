prompt --application/shared_components/user_interface/lovs/classifier_tbl_description
begin
--   Manifest
--     CLASSIFIER_TBL.DESCRIPTION
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_flow_imp_shared.create_list_of_values(
 p_id=>wwv_flow_imp.id(12965860360119334)
,p_lov_name=>'CLASSIFIER_TBL.DESCRIPTION'
,p_source_type=>'TABLE'
,p_location=>'LOCAL'
,p_query_table=>'CLASSIFIER_TBL'
,p_return_column_name=>'NAME'
,p_display_column_name=>'DESCRIPTION'
,p_default_sort_column_name=>'DESCRIPTION'
,p_default_sort_direction=>'ASC'
);
wwv_flow_imp.component_end;
end;
/
