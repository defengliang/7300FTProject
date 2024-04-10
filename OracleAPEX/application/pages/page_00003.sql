prompt --application/pages/page_00003
begin
--   Manifest
--     PAGE: 00003
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
 p_id=>3
,p_name=>'Stock Chart'
,p_alias=>'STOCK-CHART'
,p_step_title=>'Stock Chart'
,p_autocomplete_on_off=>'OFF'
,p_page_template_options=>'#DEFAULT#'
,p_page_is_public_y_n=>'Y'
,p_protection_level=>'C'
,p_page_component_map=>'04'
,p_last_updated_by=>'ADMIN'
,p_last_upd_yyyymmddhh24miss=>'20240410141338'
);
wwv_flow_imp_page.create_page_plug(
 p_id=>wwv_flow_imp.id(13554346451053820)
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
 p_id=>wwv_flow_imp.id(13554990136053822)
,p_plug_name=>'Stock Chart'
,p_region_template_options=>'#DEFAULT#:t-Region--scrollBody'
,p_plug_template=>wwv_flow_imp.id(9461276336445699)
,p_plug_display_sequence=>10
,p_include_in_reg_disp_sel_yn=>'Y'
,p_query_type=>'SQL'
,p_plug_source=>wwv_flow_string.join(wwv_flow_t_varchar2(
'select open,close, high, low, classifier, symbol, value_date from price_tbl ',
'where value_date in (select value_date from price_tbl where classifier = ''IBK'' and open > 0 and close > 0 and high > 0 and low > 0)',
'order by value_date'))
,p_plug_source_type=>'NATIVE_JET_CHART'
);
wwv_flow_imp_page.create_jet_chart(
 p_id=>wwv_flow_imp.id(13555311042053822)
,p_region_id=>wwv_flow_imp.id(13554990136053822)
,p_chart_type=>'line'
,p_height=>'400'
,p_animation_on_display=>'zoom'
,p_animation_on_data_change=>'auto'
,p_orientation=>'vertical'
,p_data_cursor=>'on'
,p_data_cursor_behavior=>'auto'
,p_hide_and_show_behavior=>'withRescale'
,p_hover_behavior=>'dim'
,p_stack=>'on'
,p_connect_nulls=>'Y'
,p_sorting=>'label-asc'
,p_fill_multi_series_gaps=>true
,p_zoom_and_scroll=>'live'
,p_initial_zooming=>'none'
,p_tooltip_rendered=>'Y'
,p_show_series_name=>true
,p_show_group_name=>true
,p_show_value=>true
,p_legend_rendered=>'on'
,p_legend_position=>'end'
,p_overview_rendered=>'on'
);
wwv_flow_imp_page.create_jet_chart_series(
 p_id=>wwv_flow_imp.id(13557057797053824)
,p_chart_id=>wwv_flow_imp.id(13555311042053822)
,p_seq=>10
,p_name=>'Open'
,p_max_row_count=>300
,p_location=>'REGION_SOURCE'
,p_series_name_column_name=>'CLASSIFIER'
,p_items_value_column_name=>'OPEN'
,p_group_short_desc_column_name=>'VALUE_DATE'
,p_items_label_column_name=>'VALUE_DATE'
,p_items_short_desc_column_name=>'SYMBOL'
,p_line_style=>'solid'
,p_line_type=>'curved'
,p_marker_rendered=>'on'
,p_marker_shape=>'auto'
,p_assigned_to_y2=>'off'
,p_items_label_rendered=>true
,p_items_label_position=>'center'
);
wwv_flow_imp_page.create_jet_chart_axis(
 p_id=>wwv_flow_imp.id(13555850637053823)
,p_chart_id=>wwv_flow_imp.id(13555311042053822)
,p_axis=>'x'
,p_is_rendered=>'on'
,p_format_scaling=>'auto'
,p_scaling=>'linear'
,p_baseline_scaling=>'zero'
,p_major_tick_rendered=>'on'
,p_minor_tick_rendered=>'on'
,p_tick_label_rendered=>'on'
,p_tick_label_rotation=>'auto'
,p_tick_label_position=>'outside'
);
wwv_flow_imp_page.create_jet_chart_axis(
 p_id=>wwv_flow_imp.id(13556428114053824)
,p_chart_id=>wwv_flow_imp.id(13555311042053822)
,p_axis=>'y'
,p_is_rendered=>'on'
,p_format_type=>'decimal'
,p_decimal_places=>0
,p_format_scaling=>'none'
,p_scaling=>'linear'
,p_baseline_scaling=>'zero'
,p_position=>'auto'
,p_major_tick_rendered=>'on'
,p_minor_tick_rendered=>'on'
,p_tick_label_rendered=>'on'
);
wwv_flow_imp.component_end;
end;
/