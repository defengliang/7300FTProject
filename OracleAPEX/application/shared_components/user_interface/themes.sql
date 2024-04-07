prompt --application/shared_components/user_interface/themes
begin
--   Manifest
--     THEME: 104
--   Manifest End
wwv_flow_imp.component_begin (
 p_version_yyyy_mm_dd=>'2023.04.28'
,p_release=>'23.1.0'
,p_default_workspace_id=>100003
,p_default_application_id=>104
,p_default_id_offset=>0
,p_default_owner=>'FTPROJECT'
);
wwv_flow_imp_shared.create_theme(
 p_id=>wwv_flow_imp.id(9619451752445769)
,p_theme_id=>42
,p_theme_name=>'Universal Theme'
,p_theme_internal_name=>'UNIVERSAL_THEME'
,p_navigation_type=>'L'
,p_nav_bar_type=>'LIST'
,p_reference_id=>4070917134413059350
,p_is_locked=>false
,p_default_page_template=>wwv_flow_imp.id(9386013889445676)
,p_default_dialog_template=>wwv_flow_imp.id(9365622435445668)
,p_error_template=>wwv_flow_imp.id(9367150017445669)
,p_printer_friendly_template=>wwv_flow_imp.id(9386013889445676)
,p_breadcrumb_display_point=>'REGION_POSITION_01'
,p_sidebar_display_point=>'REGION_POSITION_02'
,p_login_template=>wwv_flow_imp.id(9367150017445669)
,p_default_button_template=>wwv_flow_imp.id(9534462424445721)
,p_default_region_template=>wwv_flow_imp.id(9461276336445699)
,p_default_chart_template=>wwv_flow_imp.id(9461276336445699)
,p_default_form_template=>wwv_flow_imp.id(9461276336445699)
,p_default_reportr_template=>wwv_flow_imp.id(9461276336445699)
,p_default_tabform_template=>wwv_flow_imp.id(9461276336445699)
,p_default_wizard_template=>wwv_flow_imp.id(9461276336445699)
,p_default_menur_template=>wwv_flow_imp.id(9473634090445701)
,p_default_listr_template=>wwv_flow_imp.id(9461276336445699)
,p_default_irr_template=>wwv_flow_imp.id(9451483894445697)
,p_default_report_template=>wwv_flow_imp.id(9499438465445707)
,p_default_label_template=>wwv_flow_imp.id(9531934039445719)
,p_default_menu_template=>wwv_flow_imp.id(9536004203445722)
,p_default_calendar_template=>wwv_flow_imp.id(9536118793445723)
,p_default_list_template=>wwv_flow_imp.id(9530028782445718)
,p_default_nav_list_template=>wwv_flow_imp.id(9521083669445716)
,p_default_top_nav_list_temp=>wwv_flow_imp.id(9521083669445716)
,p_default_side_nav_list_temp=>wwv_flow_imp.id(9519202779445715)
,p_default_nav_list_position=>'SIDE'
,p_default_dialogbtnr_template=>wwv_flow_imp.id(9397438987445681)
,p_default_dialogr_template=>wwv_flow_imp.id(9394693381445681)
,p_default_option_label=>wwv_flow_imp.id(9531934039445719)
,p_default_required_label=>wwv_flow_imp.id(9533247390445719)
,p_default_navbar_list_template=>wwv_flow_imp.id(9522069562445716)
,p_file_prefix => nvl(wwv_flow_application_install.get_static_theme_file_prefix(42),'#APEX_FILES#themes/theme_42/23.1/')
,p_files_version=>64
,p_icon_library=>'FONTAPEX'
,p_javascript_file_urls=>wwv_flow_string.join(wwv_flow_t_varchar2(
'#APEX_FILES#libraries/apex/#MIN_DIRECTORY#widget.stickyWidget#MIN#.js?v=#APEX_VERSION#',
'#THEME_FILES#js/theme42#MIN#.js?v=#APEX_VERSION#'))
,p_css_file_urls=>'#THEME_FILES#css/Core#MIN#.css?v=#APEX_VERSION#'
);
wwv_flow_imp.component_end;
end;
/
