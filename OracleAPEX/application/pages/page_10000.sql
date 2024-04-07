prompt --application/pages/page_10000
begin
--   Manifest
--     PAGE: 10000
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_flow_imp_page.create_page(
 p_id=>10000
,p_name=>'Administration'
,p_alias=>'ADMIN'
,p_step_title=>'Administration'
,p_autocomplete_on_off=>'OFF'
,p_group_id=>wwv_flow_imp.id(9649069612445794)
,p_page_template_options=>'#DEFAULT#'
,p_required_role=>wwv_flow_imp.id(9647433598445791)
,p_protection_level=>'C'
,p_deep_linking=>'N'
,p_help_text=>wwv_flow_string.join(wwv_flow_t_varchar2(
'<p>The administration page allows application owners (Administrators) to configure the application and maintain common data used across the application.',
'By selecting one of the available settings, administrators can potentially change how the application is displayed and/or features available to the end users.</p>',
'<p>Access to this page should be limited to Administrators only.</p>'))
,p_page_component_map=>'25'
,p_last_updated_by=>'ADMIN'
,p_last_upd_yyyymmddhh24miss=>'20240405164340'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9829241933446053)
,p_plug_name=>'Breadcrumb'
,p_region_template_options=>'#DEFAULT#:t-BreadcrumbRegion--useBreadcrumbTitle'
,p_component_template_options=>'#DEFAULT#'
,p_plug_template=>wwv_flow_imp.id(9473634090445701)
,p_plug_display_sequence=>10
,p_plug_display_point=>'REGION_POSITION_01'
,p_menu_id=>wwv_flow_imp.id(9357999485445656)
,p_plug_source_type=>'NATIVE_BREADCRUMB'
,p_menu_template_id=>wwv_flow_imp.id(9536004203445722)
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9835770308446055)
,p_plug_name=>'Column 1'
,p_region_template_options=>'#DEFAULT#'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9396048426445681)
,p_plug_display_sequence=>10
,p_plug_query_num_rows=>15
,p_attribute_01=>'N'
,p_attribute_02=>'HTML'
,p_attribute_03=>'Y'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9836186853446056)
,p_plug_name=>'Configuration'
,p_parent_plug_id=>wwv_flow_imp.id(9835770308446055)
,p_region_template_options=>'#DEFAULT#:t-Region--noPadding:t-Region--scrollBody'
,p_component_template_options=>'#DEFAULT#:u-colors'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>40
,p_list_id=>wwv_flow_imp.id(9829825637446054)
,p_plug_source_type=>'NATIVE_LIST'
,p_list_template_id=>wwv_flow_imp.id(9516263722445714)
,p_plug_query_num_rows=>15
,p_required_patch=>wwv_flow_imp.id(9645195302445787)
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9836566848446056)
,p_plug_name=>'User Interface'
,p_parent_plug_id=>wwv_flow_imp.id(9835770308446055)
,p_region_template_options=>'#DEFAULT#:t-Region--noPadding:t-Region--scrollBody'
,p_component_template_options=>'#DEFAULT#:u-colors'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>50
,p_list_id=>wwv_flow_imp.id(9830523208446054)
,p_plug_source_type=>'NATIVE_LIST'
,p_list_template_id=>wwv_flow_imp.id(9516263722445714)
,p_plug_query_num_rows=>15
,p_required_patch=>wwv_flow_imp.id(9645559145445787)
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9836978543446056)
,p_plug_name=>'Activity Reports'
,p_parent_plug_id=>wwv_flow_imp.id(9835770308446055)
,p_region_template_options=>'#DEFAULT#:t-Region--noPadding:t-Region--scrollBody'
,p_component_template_options=>'#DEFAULT#:u-colors'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>60
,p_list_id=>wwv_flow_imp.id(9831271308446054)
,p_plug_source_type=>'NATIVE_LIST'
,p_list_template_id=>wwv_flow_imp.id(9516263722445714)
,p_plug_query_num_rows=>15
,p_required_patch=>wwv_flow_imp.id(9644877694445787)
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9837373999446056)
,p_plug_name=>'Column 2'
,p_region_template_options=>'#DEFAULT#'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9396048426445681)
,p_plug_display_sequence=>20
,p_plug_new_grid_row=>false
,p_plug_query_num_rows=>15
,p_attribute_01=>'N'
,p_attribute_02=>'HTML'
,p_attribute_03=>'Y'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9837792534446056)
,p_plug_name=>'Access Control'
,p_parent_plug_id=>wwv_flow_imp.id(9837373999446056)
,p_region_template_options=>'#DEFAULT#:t-Region--noPadding:t-Region--scrollBody'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>70
,p_plug_query_num_rows=>15
,p_required_patch=>wwv_flow_imp.id(9644739769445787)
,p_attribute_01=>'N'
,p_attribute_02=>'HTML'
,p_attribute_03=>'Y'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9838553476446057)
,p_plug_name=>'ACL Information'
,p_parent_plug_id=>wwv_flow_imp.id(9837792534446056)
,p_region_css_classes=>'margin-sm'
,p_region_template_options=>'#DEFAULT#:t-Alert--colorBG:t-Alert--horizontal:t-Alert--noIcon:t-Alert--warning:t-Alert--accessibleHeading'
,p_component_template_options=>'#DEFAULT#'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9389473387445677)
,p_plug_display_sequence=>10
,p_function_body_language=>'PLSQL'
,p_plug_source=>wwv_flow_string.join(wwv_flow_t_varchar2(
'declare',
'    l_acl_scope varchar2(45);',
'begin',
'    l_acl_scope := apex_app_setting.get_value( p_name => ''ACCESS_CONTROL_SCOPE'' );',
'',
'    if l_acl_scope = ''ALL_USERS'' then',
'        return apex_lang.message(''APEX.FEATURE.ACL.INFO.ALL_USERS'');',
'    elsif l_acl_scope = ''ACL_ONLY'' then',
'        return apex_lang.message(''APEX.FEATURE.ACL.INFO.ACL_ONLY'');',
'    else',
'        return apex_lang.message(''APEX.FEATURE.ACL.INFO.ACL_VALUE_INVALID'', l_acl_scope);',
'    end if;',
'end;'))
,p_plug_source_type=>'NATIVE_DYNAMIC_CONTENT'
,p_plug_query_num_rows=>15
);
wwv_flow_imp_page.create_report_region(
 p_id=>wwv_flow_imp.id(9838949335446057)
,p_name=>'User Counts Report'
,p_parent_plug_id=>wwv_flow_imp.id(9837792534446056)
,p_template=>wwv_flow_imp.id(9461276336445699)
,p_display_sequence=>20
,p_region_template_options=>'#DEFAULT#:t-Region--removeHeader:t-Region--stacked:t-Region--scrollBody:t-Region--noPadding'
,p_component_template_options=>'#DEFAULT#:t-AVPList--rightAligned'
,p_source_type=>'NATIVE_SQL_REPORT'
,p_query_type=>'SQL'
,p_source=>wwv_flow_string.join(wwv_flow_t_varchar2(
'select r.role_name, (select count(*) from apex_appl_acl_user_roles ur where r.role_id = ur.role_id) user_count, r.role_id',
'from apex_appl_acl_roles r',
'where r.application_id = :APP_ID',
'group by r.role_name, r.role_id',
'order by 2 desc, 1'))
,p_ajax_enabled=>'Y'
,p_lazy_loading=>false
,p_query_row_template=>wwv_flow_imp.id(9504420391445709)
,p_query_options=>'DERIVED_REPORT_COLUMNS'
,p_query_no_data_found=>'no data found'
,p_query_row_count_max=>500
);
wwv_flow_imp_page.create_report_columns(
 p_id=>wwv_flow_imp.id(9839641122446060)
,p_query_column_id=>1
,p_column_alias=>'ROLE_NAME'
,p_column_display_sequence=>1
,p_column_heading=>'Role Name'
,p_use_as_row_header=>'N'
,p_heading_alignment=>'LEFT'
);
wwv_flow_imp_page.create_report_columns(
 p_id=>wwv_flow_imp.id(9840069385446060)
,p_query_column_id=>2
,p_column_alias=>'USER_COUNT'
,p_column_display_sequence=>2
,p_column_heading=>'User Count'
,p_use_as_row_header=>'N'
,p_column_format=>'999G999G999G999G999G999G990'
,p_heading_alignment=>'LEFT'
);
wwv_flow_imp_page.create_report_columns(
 p_id=>wwv_flow_imp.id(9840472424446060)
,p_query_column_id=>3
,p_column_alias=>'ROLE_ID'
,p_column_display_sequence=>3
,p_column_heading=>'Role Id'
,p_use_as_row_header=>'N'
,p_heading_alignment=>'LEFT'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9843594541446064)
,p_plug_name=>'Access Control Actions'
,p_parent_plug_id=>wwv_flow_imp.id(9837792534446056)
,p_region_template_options=>'#DEFAULT#'
,p_component_template_options=>'#DEFAULT#:u-colors'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9394693381445681)
,p_plug_display_sequence=>30
,p_list_id=>wwv_flow_imp.id(9833965329446055)
,p_plug_source_type=>'NATIVE_LIST'
,p_list_template_id=>wwv_flow_imp.id(9516263722445714)
,p_plug_query_num_rows=>15
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9843983414446065)
,p_plug_name=>'Feedback'
,p_parent_plug_id=>wwv_flow_imp.id(9837373999446056)
,p_region_template_options=>'#DEFAULT#:t-Region--noPadding:t-Region--scrollBody'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>80
,p_plug_query_num_rows=>15
,p_required_patch=>wwv_flow_imp.id(9644961774445787)
,p_attribute_01=>'N'
,p_attribute_02=>'HTML'
,p_attribute_03=>'Y'
);
wwv_flow_imp_page.create_report_region(
 p_id=>wwv_flow_imp.id(9844302560446065)
,p_name=>'Report'
,p_parent_plug_id=>wwv_flow_imp.id(9843983414446065)
,p_template=>wwv_flow_imp.id(9461276336445699)
,p_display_sequence=>10
,p_region_template_options=>'#DEFAULT#:t-Region--removeHeader:t-Region--stacked:t-Region--scrollBody:t-Region--noPadding'
,p_component_template_options=>'#DEFAULT#:t-AVPList--rightAligned'
,p_source_type=>'NATIVE_SQL_REPORT'
,p_query_type=>'SQL'
,p_source=>wwv_flow_string.join(wwv_flow_t_varchar2(
'select l.display_value feedback_status, ',
'(select count(*) from apex_team_feedback f where f.application_id = :APP_ID and f.feedback_status = l.return_value) feedback_count ',
'from apex_application_lov_entries l',
'where l.application_id = :APP_ID',
'and l.list_of_values_name = ''FEEDBACK_STATUS''',
'order by 2 desc, 1'))
,p_ajax_enabled=>'Y'
,p_lazy_loading=>false
,p_query_row_template=>wwv_flow_imp.id(9504420391445709)
,p_query_options=>'DERIVED_REPORT_COLUMNS'
,p_query_no_data_found=>'no data found'
,p_query_row_count_max=>500
);
wwv_flow_imp_page.create_report_columns(
 p_id=>wwv_flow_imp.id(9845001275446067)
,p_query_column_id=>1
,p_column_alias=>'FEEDBACK_STATUS'
,p_column_display_sequence=>1
,p_column_heading=>'Feedback Status'
,p_use_as_row_header=>'N'
,p_heading_alignment=>'LEFT'
);
wwv_flow_imp_page.create_report_columns(
 p_id=>wwv_flow_imp.id(9845431484446067)
,p_query_column_id=>2
,p_column_alias=>'FEEDBACK_COUNT'
,p_column_display_sequence=>2
,p_column_heading=>'Feedback Count'
,p_use_as_row_header=>'N'
,p_column_format=>'999G999G999G999G999G999G990'
,p_heading_alignment=>'LEFT'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(9847385219446071)
,p_plug_name=>'Feedback'
,p_parent_plug_id=>wwv_flow_imp.id(9843983414446065)
,p_region_template_options=>'#DEFAULT#'
,p_component_template_options=>'#DEFAULT#:u-colors'
,p_escape_on_http_output=>'Y'
,p_plug_template=>wwv_flow_imp.id(9394693381445681)
,p_plug_display_sequence=>20
,p_list_id=>wwv_flow_imp.id(9835085297446055)
,p_plug_source_type=>'NATIVE_LIST'
,p_list_template_id=>wwv_flow_imp.id(9516263722445714)
,p_plug_query_num_rows=>15
);
wwv_flow_imp_page.create_page_button(
 p_id=>wwv_flow_imp.id(9838174230446057)
,p_button_sequence=>10
,p_button_plug_id=>wwv_flow_imp.id(9837792534446056)
,p_button_name=>'ADD_USER'
,p_button_action=>'REDIRECT_PAGE'
,p_button_template_options=>'#DEFAULT#:t-Button--noUI:t-Button--iconLeft'
,p_button_template_id=>wwv_flow_imp.id(9534580995445721)
,p_button_image_alt=>'Add User'
,p_button_position=>'EDIT'
,p_button_redirect_url=>'f?p=&APP_ID.:10042:&APP_SESSION.::&DEBUG.:RP,10042::'
,p_icon_css_classes=>'fa-user-plus'
);
wwv_flow_imp_page.create_page_da_event(
 p_id=>wwv_flow_imp.id(9842611558446064)
,p_name=>'Refresh Report'
,p_event_sequence=>10
,p_triggering_element_type=>'BUTTON'
,p_triggering_button_id=>wwv_flow_imp.id(9838174230446057)
,p_bind_type=>'bind'
,p_execution_type=>'IMMEDIATE'
,p_bind_event_type=>'apexafterclosedialog'
);
wwv_flow_imp_page.create_page_da_action(
 p_id=>wwv_flow_imp.id(9843183180446064)
,p_event_id=>wwv_flow_imp.id(9842611558446064)
,p_event_result=>'TRUE'
,p_action_sequence=>10
,p_execute_on_page_init=>'N'
,p_action=>'NATIVE_REFRESH'
,p_affected_elements_type=>'REGION'
,p_affected_region_id=>wwv_flow_imp.id(9838949335446057)
);
wwv_flow_imp.component_end;
end;
/
