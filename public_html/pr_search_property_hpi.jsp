<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>

<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>
<jsp:useBean id="codeControl" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<%
	Properties_IC_MP_SP search_info = searchControl.searchPropertyAllInfo(request);
	String param_TB = request.getParameter("tb_option"); //대분류
	String param_DB = request.getParameter("db_option"); //데이터분류
	String param_part1 = request.getParameter("part_1_id"); //표적입자
	String param_part1_sym = request.getParameter("part_1_sym"); //표적입자
	String param_part2 = ComUtil.isNullToDashString(request.getParameter("part_2_id")); //입사입자
	String param_part2_sym = ComUtil.isNullToDashString(request.getParameter("part_2_sym")); //입사입자
	String param_MP = request.getParameter("mp_option"); //주프로세스
	String param_srd = request.getParameter("srd_value"); //참조표준인증여부
	String search_exp = "";
	if(!param_part2.equalsIgnoreCase("-")){
		search_exp = "표적입자 : "+param_part2_sym + ", 입사입자 : " + param_part1_sym;
	}else{
		search_exp = "입사입자 : " + param_part1_sym;
	}
	search_exp = search_exp + ", 대분류 : " +codeControl.getExpName(param_TB);
	search_exp = search_exp + ", 데이터분류 : " +codeControl.getExpName(param_DB);
	
	//Heavy particle Impact 관련 항목
	//Heavy Particle Impact - Scattering
	String HSCAT_Total = ComUtil.isNullToZeroString(search_info.getHSCAT_Total());
	String HSCAT_Elastic = ComUtil.isNullToZeroString(search_info.getHSCAT_Elastic());
	String HSCAT_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHSCAT_Momentum_Transfer());
	String HSCAT_Single = ComUtil.isNullToZeroString(search_info.getHSCAT_Single());
	String HSCAT_Double = ComUtil.isNullToZeroString(search_info.getHSCAT_Double());
	String HSCAT_Multiple = ComUtil.isNullToZeroString(search_info.getHSCAT_Multiple());
	String HSCAT_Partial = ComUtil.isNullToZeroString(search_info.getHSCAT_Partial());
	String HSCAT_Associative = ComUtil.isNullToZeroString(search_info.getHSCAT_Associative());
	String HSCAT_Penning = ComUtil.isNullToZeroString(search_info.getHSCAT_Penning());
	String HSCAT_Reaction = ComUtil.isNullToZeroString(search_info.getHSCAT_Reaction());
	String HSCAT_Electronic = ComUtil.isNullToZeroString(search_info.getHSCAT_Electronic());
	String HSCAT_Vibrational = ComUtil.isNullToZeroString(search_info.getHSCAT_Vibrational());
	String HSCAT_Rotational = ComUtil.isNullToZeroString(search_info.getHSCAT_Rotational());
	String HSCAT_Radiative = ComUtil.isNullToZeroString(search_info.getHSCAT_Radiative());
	String HSCAT_Dielectronic = ComUtil.isNullToZeroString(search_info.getHSCAT_Dielectronic());
	String HSCAT_Three_Body = ComUtil.isNullToZeroString(search_info.getHSCAT_Three_Body());
	String HSCAT_Dissociative = ComUtil.isNullToZeroString(search_info.getHSCAT_Dissociative());
	String HSCAT_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHSCAT_Transfer_Ionization());
	String HSCAT_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHSCAT_Thermal_Electron());
	String HSCAT_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHSCAT_Electron_Loss());
	String HSCAT_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHSCAT_Particle_Interchange());
	String HSCAT_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHSCAT_Total_Neutral_Fragments());
	String HSCAT_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHSCAT_Neutral_Fragments());
	String HSCAT_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHSCAT_Total_Dissociative());
	String HSCAT_Detachment = ComUtil.isNullToZeroString(search_info.getHSCAT_Detachment());
	String HSCAT_Autoionization = ComUtil.isNullToZeroString(search_info.getHSCAT_Autoionization());
	String HSCAT_Quenching = ComUtil.isNullToZeroString(search_info.getHSCAT_Quenching());
	String HSCAT_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHSCAT_X_ray_Production());
	String HSCAT_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHSCAT_Relacxation_Reaction());
	String HSCAT_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHSCAT_State_Selectivity());
	String HSCAT_Photon = ComUtil.isNullToZeroString(search_info.getHSCAT_Photon());
	String HSCAT_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHSCAT_Ion_Pair_Production());
	String HSCAT_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHSCAT_Charge_Transfer());
	String HSCAT_de_Excitation = ComUtil.isNullToZeroString(search_info.getHSCAT_de_Excitation());
	String HSCAT_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHSCAT_Neutral_Product_Dissociation());
	String HSCAT_Attachement = ComUtil.isNullToZeroString(search_info.getHSCAT_Attachement());
	String HSCAT_Inelastic = ComUtil.isNullToZeroString(search_info.getHSCAT_Inelastic());
	String HSCAT_Electron_Production = ComUtil.isNullToZeroString(search_info.getHSCAT_Electron_Production());
	String HSCAT_Vibrotational = ComUtil.isNullToZeroString(search_info.getHSCAT_Vibrotational());
	
	//Heavy Particle Impact - Ionization
	String HIONI_Total = ComUtil.isNullToZeroString(search_info.getHIONI_Total());
	String HIONI_Elastic = ComUtil.isNullToZeroString(search_info.getHIONI_Elastic());
	String HIONI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHIONI_Momentum_Transfer());
	String HIONI_Single = ComUtil.isNullToZeroString(search_info.getHIONI_Single());
	String HIONI_Double = ComUtil.isNullToZeroString(search_info.getHIONI_Double());
	String HIONI_Multiple = ComUtil.isNullToZeroString(search_info.getHIONI_Multiple());
	String HIONI_Partial = ComUtil.isNullToZeroString(search_info.getHIONI_Partial());
	String HIONI_Associative = ComUtil.isNullToZeroString(search_info.getHIONI_Associative());
	String HIONI_Penning = ComUtil.isNullToZeroString(search_info.getHIONI_Penning());
	String HIONI_Reaction = ComUtil.isNullToZeroString(search_info.getHIONI_Reaction());
	String HIONI_Electronic = ComUtil.isNullToZeroString(search_info.getHIONI_Electronic());
	String HIONI_Vibrational = ComUtil.isNullToZeroString(search_info.getHIONI_Vibrational());
	String HIONI_Rotational = ComUtil.isNullToZeroString(search_info.getHIONI_Rotational());
	String HIONI_Radiative = ComUtil.isNullToZeroString(search_info.getHIONI_Radiative());
	String HIONI_Dielectronic = ComUtil.isNullToZeroString(search_info.getHIONI_Dielectronic());
	String HIONI_Three_Body = ComUtil.isNullToZeroString(search_info.getHIONI_Three_Body());
	String HIONI_Dissociative = ComUtil.isNullToZeroString(search_info.getHIONI_Dissociative());
	String HIONI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHIONI_Transfer_Ionization());
	String HIONI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHIONI_Thermal_Electron());
	String HIONI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHIONI_Electron_Loss());
	String HIONI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHIONI_Particle_Interchange());
	String HIONI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHIONI_Total_Neutral_Fragments());
	String HIONI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHIONI_Neutral_Fragments());
	String HIONI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHIONI_Total_Dissociative());
	String HIONI_Detachment = ComUtil.isNullToZeroString(search_info.getHIONI_Detachment());
	String HIONI_Autoionization = ComUtil.isNullToZeroString(search_info.getHIONI_Autoionization());
	String HIONI_Quenching = ComUtil.isNullToZeroString(search_info.getHIONI_Quenching());
	String HIONI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHIONI_X_ray_Production());
	String HIONI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHIONI_Relacxation_Reaction());
	String HIONI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHIONI_State_Selectivity());
	String HIONI_Photon = ComUtil.isNullToZeroString(search_info.getHIONI_Photon());
	String HIONI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHIONI_Ion_Pair_Production());
	String HIONI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHIONI_Charge_Transfer());
	String HIONI_de_Excitation = ComUtil.isNullToZeroString(search_info.getHIONI_de_Excitation());
	String HIONI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHIONI_Neutral_Product_Dissociation());
	String HIONI_Attachement = ComUtil.isNullToZeroString(search_info.getHIONI_Attachement());
	String HIONI_Inelastic = ComUtil.isNullToZeroString(search_info.getHIONI_Inelastic());
	String HIONI_Electron_Production = ComUtil.isNullToZeroString(search_info.getHIONI_Electron_Production());
	String HIONI_Vibrotational = ComUtil.isNullToZeroString(search_info.getHIONI_Vibrotational());
	
	//Heavy Particle Impact - Excitation
	String HEXCI_Total = ComUtil.isNullToZeroString(search_info.getHEXCI_Total());
	String HEXCI_Elastic = ComUtil.isNullToZeroString(search_info.getHEXCI_Elastic());
	String HEXCI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHEXCI_Momentum_Transfer());
	String HEXCI_Single = ComUtil.isNullToZeroString(search_info.getHEXCI_Single());
	String HEXCI_Double = ComUtil.isNullToZeroString(search_info.getHEXCI_Double());
	String HEXCI_Multiple = ComUtil.isNullToZeroString(search_info.getHEXCI_Multiple());
	String HEXCI_Partial = ComUtil.isNullToZeroString(search_info.getHEXCI_Partial());
	String HEXCI_Associative = ComUtil.isNullToZeroString(search_info.getHEXCI_Associative());
	String HEXCI_Penning = ComUtil.isNullToZeroString(search_info.getHEXCI_Penning());
	String HEXCI_Reaction = ComUtil.isNullToZeroString(search_info.getHEXCI_Reaction());
	String HEXCI_Electronic = ComUtil.isNullToZeroString(search_info.getHEXCI_Electronic());
	String HEXCI_Vibrational = ComUtil.isNullToZeroString(search_info.getHEXCI_Vibrational());
	String HEXCI_Rotational = ComUtil.isNullToZeroString(search_info.getHEXCI_Rotational());
	String HEXCI_Radiative = ComUtil.isNullToZeroString(search_info.getHEXCI_Radiative());
	String HEXCI_Dielectronic = ComUtil.isNullToZeroString(search_info.getHEXCI_Dielectronic());
	String HEXCI_Three_Body = ComUtil.isNullToZeroString(search_info.getHEXCI_Three_Body());
	String HEXCI_Dissociative = ComUtil.isNullToZeroString(search_info.getHEXCI_Dissociative());
	String HEXCI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHEXCI_Transfer_Ionization());
	String HEXCI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHEXCI_Thermal_Electron());
	String HEXCI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHEXCI_Electron_Loss());
	String HEXCI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHEXCI_Particle_Interchange());
	String HEXCI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHEXCI_Total_Neutral_Fragments());
	String HEXCI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHEXCI_Neutral_Fragments());
	String HEXCI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHEXCI_Total_Dissociative());
	String HEXCI_Detachment = ComUtil.isNullToZeroString(search_info.getHEXCI_Detachment());
	String HEXCI_Autoionization = ComUtil.isNullToZeroString(search_info.getHEXCI_Autoionization());
	String HEXCI_Quenching = ComUtil.isNullToZeroString(search_info.getHEXCI_Quenching());
	String HEXCI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHEXCI_X_ray_Production());
	String HEXCI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHEXCI_Relacxation_Reaction());
	String HEXCI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHEXCI_State_Selectivity());
	String HEXCI_Photon = ComUtil.isNullToZeroString(search_info.getHEXCI_Photon());
	String HEXCI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHEXCI_Ion_Pair_Production());
	String HEXCI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHEXCI_Charge_Transfer());
	String HEXCI_de_Excitation = ComUtil.isNullToZeroString(search_info.getHEXCI_de_Excitation());
	String HEXCI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHEXCI_Neutral_Product_Dissociation());
	String HEXCI_Attachement = ComUtil.isNullToZeroString(search_info.getHEXCI_Attachement());
	String HEXCI_Inelastic = ComUtil.isNullToZeroString(search_info.getHEXCI_Inelastic());
	String HEXCI_Electron_Production = ComUtil.isNullToZeroString(search_info.getHEXCI_Electron_Production());
	String HEXCI_Vibrotational = ComUtil.isNullToZeroString(search_info.getHEXCI_Vibrotational());
	
	//Heavy Particle Impact - Recombination
	String HRECO_Total = ComUtil.isNullToZeroString(search_info.getHRECO_Total());
	String HRECO_Elastic = ComUtil.isNullToZeroString(search_info.getHRECO_Elastic());
	String HRECO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHRECO_Momentum_Transfer());
	String HRECO_Single = ComUtil.isNullToZeroString(search_info.getHRECO_Single());
	String HRECO_Double = ComUtil.isNullToZeroString(search_info.getHRECO_Double());
	String HRECO_Multiple = ComUtil.isNullToZeroString(search_info.getHRECO_Multiple());
	String HRECO_Partial = ComUtil.isNullToZeroString(search_info.getHRECO_Partial());
	String HRECO_Associative = ComUtil.isNullToZeroString(search_info.getHRECO_Associative());
	String HRECO_Penning = ComUtil.isNullToZeroString(search_info.getHRECO_Penning());
	String HRECO_Reaction = ComUtil.isNullToZeroString(search_info.getHRECO_Reaction());
	String HRECO_Electronic = ComUtil.isNullToZeroString(search_info.getHRECO_Electronic());
	String HRECO_Vibrational = ComUtil.isNullToZeroString(search_info.getHRECO_Vibrational());
	String HRECO_Rotational = ComUtil.isNullToZeroString(search_info.getHRECO_Rotational());
	String HRECO_Radiative = ComUtil.isNullToZeroString(search_info.getHRECO_Radiative());
	String HRECO_Dielectronic = ComUtil.isNullToZeroString(search_info.getHRECO_Dielectronic());
	String HRECO_Three_Body = ComUtil.isNullToZeroString(search_info.getHRECO_Three_Body());
	String HRECO_Dissociative = ComUtil.isNullToZeroString(search_info.getHRECO_Dissociative());
	String HRECO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHRECO_Transfer_Ionization());
	String HRECO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHRECO_Thermal_Electron());
	String HRECO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHRECO_Electron_Loss());
	String HRECO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHRECO_Particle_Interchange());
	String HRECO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHRECO_Total_Neutral_Fragments());
	String HRECO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHRECO_Neutral_Fragments());
	String HRECO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHRECO_Total_Dissociative());
	String HRECO_Detachment = ComUtil.isNullToZeroString(search_info.getHRECO_Detachment());
	String HRECO_Autoionization = ComUtil.isNullToZeroString(search_info.getHRECO_Autoionization());
	String HRECO_Quenching = ComUtil.isNullToZeroString(search_info.getHRECO_Quenching());
	String HRECO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHRECO_X_ray_Production());
	String HRECO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHRECO_Relacxation_Reaction());
	String HRECO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHRECO_State_Selectivity());
	String HRECO_Photon = ComUtil.isNullToZeroString(search_info.getHRECO_Photon());
	String HRECO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHRECO_Ion_Pair_Production());
	String HRECO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHRECO_Charge_Transfer());
	String HRECO_de_Excitation = ComUtil.isNullToZeroString(search_info.getHRECO_de_Excitation());
	String HRECO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHRECO_Neutral_Product_Dissociation());
	String HRECO_Attachement = ComUtil.isNullToZeroString(search_info.getHRECO_Attachement());
	String HRECO_Inelastic = ComUtil.isNullToZeroString(search_info.getHRECO_Inelastic());
	String HRECO_Electron_Production = ComUtil.isNullToZeroString(search_info.getHRECO_Electron_Production());
	String HRECO_Vibrotational = ComUtil.isNullToZeroString(search_info.getHRECO_Vibrotational());           
	
	
	//Heavy Particle Impact - Charge Transfer
	String HCHTR_Total = ComUtil.isNullToZeroString(search_info.getHCHTR_Total());
	String HCHTR_Elastic = ComUtil.isNullToZeroString(search_info.getHCHTR_Elastic());
	String HCHTR_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHCHTR_Momentum_Transfer());
	String HCHTR_Single = ComUtil.isNullToZeroString(search_info.getHCHTR_Single());
	String HCHTR_Double = ComUtil.isNullToZeroString(search_info.getHCHTR_Double());
	String HCHTR_Multiple = ComUtil.isNullToZeroString(search_info.getHCHTR_Multiple());
	String HCHTR_Partial = ComUtil.isNullToZeroString(search_info.getHCHTR_Partial());
	String HCHTR_Associative = ComUtil.isNullToZeroString(search_info.getHCHTR_Associative());
	String HCHTR_Penning = ComUtil.isNullToZeroString(search_info.getHCHTR_Penning());
	String HCHTR_Reaction = ComUtil.isNullToZeroString(search_info.getHCHTR_Reaction());
	String HCHTR_Electronic = ComUtil.isNullToZeroString(search_info.getHCHTR_Electronic());
	String HCHTR_Vibrational = ComUtil.isNullToZeroString(search_info.getHCHTR_Vibrational());
	String HCHTR_Rotational = ComUtil.isNullToZeroString(search_info.getHCHTR_Rotational());
	String HCHTR_Radiative = ComUtil.isNullToZeroString(search_info.getHCHTR_Radiative());
	String HCHTR_Dielectronic = ComUtil.isNullToZeroString(search_info.getHCHTR_Dielectronic());
	String HCHTR_Three_Body = ComUtil.isNullToZeroString(search_info.getHCHTR_Three_Body());
	String HCHTR_Dissociative = ComUtil.isNullToZeroString(search_info.getHCHTR_Dissociative());
	String HCHTR_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHCHTR_Transfer_Ionization());
	String HCHTR_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHCHTR_Thermal_Electron());
	String HCHTR_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHCHTR_Electron_Loss());
	String HCHTR_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHCHTR_Particle_Interchange());
	String HCHTR_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHCHTR_Total_Neutral_Fragments());
	String HCHTR_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHCHTR_Neutral_Fragments());
	String HCHTR_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHCHTR_Total_Dissociative());
	String HCHTR_Detachment = ComUtil.isNullToZeroString(search_info.getHCHTR_Detachment());
	String HCHTR_Autoionization = ComUtil.isNullToZeroString(search_info.getHCHTR_Autoionization());
	String HCHTR_Quenching = ComUtil.isNullToZeroString(search_info.getHCHTR_Quenching());
	String HCHTR_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHCHTR_X_ray_Production());
	String HCHTR_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHCHTR_Relacxation_Reaction());
	String HCHTR_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHCHTR_State_Selectivity());
	String HCHTR_Photon = ComUtil.isNullToZeroString(search_info.getHCHTR_Photon());
	String HCHTR_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHCHTR_Ion_Pair_Production());
	String HCHTR_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHCHTR_Charge_Transfer());
	String HCHTR_de_Excitation = ComUtil.isNullToZeroString(search_info.getHCHTR_de_Excitation());
	String HCHTR_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHCHTR_Neutral_Product_Dissociation());
	String HCHTR_Attachement = ComUtil.isNullToZeroString(search_info.getHCHTR_Attachement());
	String HCHTR_Inelastic = ComUtil.isNullToZeroString(search_info.getHCHTR_Inelastic());
	String HCHTR_Electron_Production = ComUtil.isNullToZeroString(search_info.getHCHTR_Electron_Production());
	String HCHTR_Vibrotational = ComUtil.isNullToZeroString(search_info.getHCHTR_Vibrotational());
	
	//Heavy Particle Impact - Dissociation
	String HDISS_Total = ComUtil.isNullToZeroString(search_info.getHDISS_Total());
	String HDISS_Elastic = ComUtil.isNullToZeroString(search_info.getHDISS_Elastic());
	String HDISS_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHDISS_Momentum_Transfer());
	String HDISS_Single = ComUtil.isNullToZeroString(search_info.getHDISS_Single());
	String HDISS_Double = ComUtil.isNullToZeroString(search_info.getHDISS_Double());
	String HDISS_Multiple = ComUtil.isNullToZeroString(search_info.getHDISS_Multiple());
	String HDISS_Partial = ComUtil.isNullToZeroString(search_info.getHDISS_Partial());
	String HDISS_Associative = ComUtil.isNullToZeroString(search_info.getHDISS_Associative());
	String HDISS_Penning = ComUtil.isNullToZeroString(search_info.getHDISS_Penning());
	String HDISS_Reaction = ComUtil.isNullToZeroString(search_info.getHDISS_Reaction());
	String HDISS_Electronic = ComUtil.isNullToZeroString(search_info.getHDISS_Electronic());
	String HDISS_Vibrational = ComUtil.isNullToZeroString(search_info.getHDISS_Vibrational());
	String HDISS_Rotational = ComUtil.isNullToZeroString(search_info.getHDISS_Rotational());
	String HDISS_Radiative = ComUtil.isNullToZeroString(search_info.getHDISS_Radiative());
	String HDISS_Dielectronic = ComUtil.isNullToZeroString(search_info.getHDISS_Dielectronic());
	String HDISS_Three_Body = ComUtil.isNullToZeroString(search_info.getHDISS_Three_Body());
	String HDISS_Dissociative = ComUtil.isNullToZeroString(search_info.getHDISS_Dissociative());
	String HDISS_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHDISS_Transfer_Ionization());
	String HDISS_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHDISS_Thermal_Electron());
	String HDISS_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHDISS_Electron_Loss());
	String HDISS_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHDISS_Particle_Interchange());
	String HDISS_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHDISS_Total_Neutral_Fragments());
	String HDISS_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHDISS_Neutral_Fragments());
	String HDISS_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHDISS_Total_Dissociative());
	String HDISS_Detachment = ComUtil.isNullToZeroString(search_info.getHDISS_Detachment());
	String HDISS_Autoionization = ComUtil.isNullToZeroString(search_info.getHDISS_Autoionization());
	String HDISS_Quenching = ComUtil.isNullToZeroString(search_info.getHDISS_Quenching());
	String HDISS_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHDISS_X_ray_Production());
	String HDISS_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHDISS_Relacxation_Reaction());
	String HDISS_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHDISS_State_Selectivity());
	String HDISS_Photon = ComUtil.isNullToZeroString(search_info.getHDISS_Photon());
	String HDISS_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHDISS_Ion_Pair_Production());
	String HDISS_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHDISS_Charge_Transfer());
	String HDISS_de_Excitation = ComUtil.isNullToZeroString(search_info.getHDISS_de_Excitation());
	String HDISS_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHDISS_Neutral_Product_Dissociation());
	String HDISS_Attachement = ComUtil.isNullToZeroString(search_info.getHDISS_Attachement());
	String HDISS_Inelastic = ComUtil.isNullToZeroString(search_info.getHDISS_Inelastic());
	String HDISS_Electron_Production = ComUtil.isNullToZeroString(search_info.getHDISS_Electron_Production());
	String HDISS_Vibrotational = ComUtil.isNullToZeroString(search_info.getHDISS_Vibrotational());    
	
	//Heavy Particle Impact - Attachment
	String HATTA_Total = ComUtil.isNullToZeroString(search_info.getHATTA_Total());
	String HATTA_Elastic = ComUtil.isNullToZeroString(search_info.getHATTA_Elastic());
	String HATTA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHATTA_Momentum_Transfer());
	String HATTA_Single = ComUtil.isNullToZeroString(search_info.getHATTA_Single());
	String HATTA_Double = ComUtil.isNullToZeroString(search_info.getHATTA_Double());
	String HATTA_Multiple = ComUtil.isNullToZeroString(search_info.getHATTA_Multiple());
	String HATTA_Partial = ComUtil.isNullToZeroString(search_info.getHATTA_Partial());
	String HATTA_Associative = ComUtil.isNullToZeroString(search_info.getHATTA_Associative());
	String HATTA_Penning = ComUtil.isNullToZeroString(search_info.getHATTA_Penning());
	String HATTA_Reaction = ComUtil.isNullToZeroString(search_info.getHATTA_Reaction());
	String HATTA_Electronic = ComUtil.isNullToZeroString(search_info.getHATTA_Electronic());
	String HATTA_Vibrational = ComUtil.isNullToZeroString(search_info.getHATTA_Vibrational());
	String HATTA_Rotational = ComUtil.isNullToZeroString(search_info.getHATTA_Rotational());
	String HATTA_Radiative = ComUtil.isNullToZeroString(search_info.getHATTA_Radiative());
	String HATTA_Dielectronic = ComUtil.isNullToZeroString(search_info.getHATTA_Dielectronic());
	String HATTA_Three_Body = ComUtil.isNullToZeroString(search_info.getHATTA_Three_Body());
	String HATTA_Dissociative = ComUtil.isNullToZeroString(search_info.getHATTA_Dissociative());
	String HATTA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHATTA_Transfer_Ionization());
	String HATTA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHATTA_Thermal_Electron());
	String HATTA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHATTA_Electron_Loss());
	String HATTA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHATTA_Particle_Interchange());
	String HATTA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHATTA_Total_Neutral_Fragments());
	String HATTA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHATTA_Neutral_Fragments());
	String HATTA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHATTA_Total_Dissociative());
	String HATTA_Detachment = ComUtil.isNullToZeroString(search_info.getHATTA_Detachment());
	String HATTA_Autoionization = ComUtil.isNullToZeroString(search_info.getHATTA_Autoionization());
	String HATTA_Quenching = ComUtil.isNullToZeroString(search_info.getHATTA_Quenching());
	String HATTA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHATTA_X_ray_Production());
	String HATTA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHATTA_Relacxation_Reaction());
	String HATTA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHATTA_State_Selectivity());
	String HATTA_Photon = ComUtil.isNullToZeroString(search_info.getHATTA_Photon());
	String HATTA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHATTA_Ion_Pair_Production());
	String HATTA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHATTA_Charge_Transfer());
	String HATTA_de_Excitation = ComUtil.isNullToZeroString(search_info.getHATTA_de_Excitation());
	String HATTA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHATTA_Neutral_Product_Dissociation());
	String HATTA_Attachement = ComUtil.isNullToZeroString(search_info.getHATTA_Attachement());
	String HATTA_Inelastic = ComUtil.isNullToZeroString(search_info.getHATTA_Inelastic());
	String HATTA_Electron_Production = ComUtil.isNullToZeroString(search_info.getHATTA_Electron_Production());
	String HATTA_Vibrotational = ComUtil.isNullToZeroString(search_info.getHATTA_Vibrotational());     
	
	//Heavy Particle Impact - Reaction
	String HREAC_Total = ComUtil.isNullToZeroString(search_info.getHREAC_Total());
	String HREAC_Elastic = ComUtil.isNullToZeroString(search_info.getHREAC_Elastic());
	String HREAC_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHREAC_Momentum_Transfer());
	String HREAC_Single = ComUtil.isNullToZeroString(search_info.getHREAC_Single());
	String HREAC_Double = ComUtil.isNullToZeroString(search_info.getHREAC_Double());
	String HREAC_Multiple = ComUtil.isNullToZeroString(search_info.getHREAC_Multiple());
	String HREAC_Partial = ComUtil.isNullToZeroString(search_info.getHREAC_Partial());
	String HREAC_Associative = ComUtil.isNullToZeroString(search_info.getHREAC_Associative());
	String HREAC_Penning = ComUtil.isNullToZeroString(search_info.getHREAC_Penning());
	String HREAC_Reaction = ComUtil.isNullToZeroString(search_info.getHREAC_Reaction());
	String HREAC_Electronic = ComUtil.isNullToZeroString(search_info.getHREAC_Electronic());
	String HREAC_Vibrational = ComUtil.isNullToZeroString(search_info.getHREAC_Vibrational());
	String HREAC_Rotational = ComUtil.isNullToZeroString(search_info.getHREAC_Rotational());
	String HREAC_Radiative = ComUtil.isNullToZeroString(search_info.getHREAC_Radiative());
	String HREAC_Dielectronic = ComUtil.isNullToZeroString(search_info.getHREAC_Dielectronic());
	String HREAC_Three_Body = ComUtil.isNullToZeroString(search_info.getHREAC_Three_Body());
	String HREAC_Dissociative = ComUtil.isNullToZeroString(search_info.getHREAC_Dissociative());
	String HREAC_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHREAC_Transfer_Ionization());
	String HREAC_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHREAC_Thermal_Electron());
	String HREAC_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHREAC_Electron_Loss());
	String HREAC_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHREAC_Particle_Interchange());
	String HREAC_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHREAC_Total_Neutral_Fragments());
	String HREAC_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHREAC_Neutral_Fragments());
	String HREAC_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHREAC_Total_Dissociative());
	String HREAC_Detachment = ComUtil.isNullToZeroString(search_info.getHREAC_Detachment());
	String HREAC_Autoionization = ComUtil.isNullToZeroString(search_info.getHREAC_Autoionization());
	String HREAC_Quenching = ComUtil.isNullToZeroString(search_info.getHREAC_Quenching());
	String HREAC_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHREAC_X_ray_Production());
	String HREAC_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHREAC_Relacxation_Reaction());
	String HREAC_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHREAC_State_Selectivity());
	String HREAC_Photon = ComUtil.isNullToZeroString(search_info.getHREAC_Photon());
	String HREAC_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHREAC_Ion_Pair_Production());
	String HREAC_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHREAC_Charge_Transfer());
	String HREAC_de_Excitation = ComUtil.isNullToZeroString(search_info.getHREAC_de_Excitation());
	String HREAC_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHREAC_Neutral_Product_Dissociation());
	String HREAC_Attachement = ComUtil.isNullToZeroString(search_info.getHREAC_Attachement());
	String HREAC_Inelastic = ComUtil.isNullToZeroString(search_info.getHREAC_Inelastic());
	String HREAC_Electron_Production = ComUtil.isNullToZeroString(search_info.getHREAC_Electron_Production());
	String HREAC_Vibrotational = ComUtil.isNullToZeroString(search_info.getHREAC_Vibrotational());  
	
	//Heavy Particle Impact - Detachment     
	String HDETA_Total = ComUtil.isNullToZeroString(search_info.getHDETA_Total());
	String HDETA_Elastic = ComUtil.isNullToZeroString(search_info.getHDETA_Elastic());
	String HDETA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHDETA_Momentum_Transfer());
	String HDETA_Single = ComUtil.isNullToZeroString(search_info.getHDETA_Single());
	String HDETA_Double = ComUtil.isNullToZeroString(search_info.getHDETA_Double());
	String HDETA_Multiple = ComUtil.isNullToZeroString(search_info.getHDETA_Multiple());
	String HDETA_Partial = ComUtil.isNullToZeroString(search_info.getHDETA_Partial());
	String HDETA_Associative = ComUtil.isNullToZeroString(search_info.getHDETA_Associative());
	String HDETA_Penning = ComUtil.isNullToZeroString(search_info.getHDETA_Penning());
	String HDETA_Reaction = ComUtil.isNullToZeroString(search_info.getHDETA_Reaction());
	String HDETA_Electronic = ComUtil.isNullToZeroString(search_info.getHDETA_Electronic());
	String HDETA_Vibrational = ComUtil.isNullToZeroString(search_info.getHDETA_Vibrational());
	String HDETA_Rotational = ComUtil.isNullToZeroString(search_info.getHDETA_Rotational());
	String HDETA_Radiative = ComUtil.isNullToZeroString(search_info.getHDETA_Radiative());
	String HDETA_Dielectronic = ComUtil.isNullToZeroString(search_info.getHDETA_Dielectronic());
	String HDETA_Three_Body = ComUtil.isNullToZeroString(search_info.getHDETA_Three_Body());
	String HDETA_Dissociative = ComUtil.isNullToZeroString(search_info.getHDETA_Dissociative());
	String HDETA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHDETA_Transfer_Ionization());
	String HDETA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHDETA_Thermal_Electron());
	String HDETA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHDETA_Electron_Loss());
	String HDETA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHDETA_Particle_Interchange());
	String HDETA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHDETA_Total_Neutral_Fragments());
	String HDETA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHDETA_Neutral_Fragments());
	String HDETA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHDETA_Total_Dissociative());
	String HDETA_Detachment = ComUtil.isNullToZeroString(search_info.getHDETA_Detachment());
	String HDETA_Autoionization = ComUtil.isNullToZeroString(search_info.getHDETA_Autoionization());
	String HDETA_Quenching = ComUtil.isNullToZeroString(search_info.getHDETA_Quenching());
	String HDETA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHDETA_X_ray_Production());
	String HDETA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHDETA_Relacxation_Reaction());
	String HDETA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHDETA_State_Selectivity());
	String HDETA_Photon = ComUtil.isNullToZeroString(search_info.getHDETA_Photon());
	String HDETA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHDETA_Ion_Pair_Production());
	String HDETA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHDETA_Charge_Transfer());
	String HDETA_de_Excitation = ComUtil.isNullToZeroString(search_info.getHDETA_de_Excitation());
	String HDETA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHDETA_Neutral_Product_Dissociation());
	String HDETA_Attachement = ComUtil.isNullToZeroString(search_info.getHDETA_Attachement());
	String HDETA_Inelastic = ComUtil.isNullToZeroString(search_info.getHDETA_Inelastic());
	String HDETA_Electron_Production = ComUtil.isNullToZeroString(search_info.getHDETA_Electron_Production());
	String HDETA_Vibrotational = ComUtil.isNullToZeroString(search_info.getHDETA_Vibrotational()); 
	
	//Heavy Particle Impact - Absorption 
	String HABSO_Total = ComUtil.isNullToZeroString(search_info.getHABSO_Total());
	String HABSO_Elastic = ComUtil.isNullToZeroString(search_info.getHABSO_Elastic());
	String HABSO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getHABSO_Momentum_Transfer());
	String HABSO_Single = ComUtil.isNullToZeroString(search_info.getHABSO_Single());
	String HABSO_Double = ComUtil.isNullToZeroString(search_info.getHABSO_Double());
	String HABSO_Multiple = ComUtil.isNullToZeroString(search_info.getHABSO_Multiple());
	String HABSO_Partial = ComUtil.isNullToZeroString(search_info.getHABSO_Partial());
	String HABSO_Associative = ComUtil.isNullToZeroString(search_info.getHABSO_Associative());
	String HABSO_Penning = ComUtil.isNullToZeroString(search_info.getHABSO_Penning());
	String HABSO_Reaction = ComUtil.isNullToZeroString(search_info.getHABSO_Reaction());
	String HABSO_Electronic = ComUtil.isNullToZeroString(search_info.getHABSO_Electronic());
	String HABSO_Vibrational = ComUtil.isNullToZeroString(search_info.getHABSO_Vibrational());
	String HABSO_Rotational = ComUtil.isNullToZeroString(search_info.getHABSO_Rotational());
	String HABSO_Radiative = ComUtil.isNullToZeroString(search_info.getHABSO_Radiative());
	String HABSO_Dielectronic = ComUtil.isNullToZeroString(search_info.getHABSO_Dielectronic());
	String HABSO_Three_Body = ComUtil.isNullToZeroString(search_info.getHABSO_Three_Body());
	String HABSO_Dissociative = ComUtil.isNullToZeroString(search_info.getHABSO_Dissociative());
	String HABSO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getHABSO_Transfer_Ionization());
	String HABSO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getHABSO_Thermal_Electron());
	String HABSO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getHABSO_Electron_Loss());
	String HABSO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getHABSO_Particle_Interchange());
	String HABSO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHABSO_Total_Neutral_Fragments());
	String HABSO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getHABSO_Neutral_Fragments());
	String HABSO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getHABSO_Total_Dissociative());
	String HABSO_Detachment = ComUtil.isNullToZeroString(search_info.getHABSO_Detachment());
	String HABSO_Autoionization = ComUtil.isNullToZeroString(search_info.getHABSO_Autoionization());
	String HABSO_Quenching = ComUtil.isNullToZeroString(search_info.getHABSO_Quenching());
	String HABSO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getHABSO_X_ray_Production());
	String HABSO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getHABSO_Relacxation_Reaction());
	String HABSO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getHABSO_State_Selectivity());
	String HABSO_Photon = ComUtil.isNullToZeroString(search_info.getHABSO_Photon());
	String HABSO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getHABSO_Ion_Pair_Production());
	String HABSO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getHABSO_Charge_Transfer());
	String HABSO_de_Excitation = ComUtil.isNullToZeroString(search_info.getHABSO_de_Excitation());
	String HABSO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getHABSO_Neutral_Product_Dissociation());
	String HABSO_Attachement = ComUtil.isNullToZeroString(search_info.getHABSO_Attachement());
	String HABSO_Inelastic = ComUtil.isNullToZeroString(search_info.getHABSO_Inelastic());
	String HABSO_Electron_Production = ComUtil.isNullToZeroString(search_info.getHABSO_Electron_Production());
	String HABSO_Vibrotational = ComUtil.isNullToZeroString(search_info.getHABSO_Vibrotational()); 

	String HI_COUNT = ComUtil.isNullToZeroString(search_info.getH_COUNT());
	
	String gotoindex = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>물성정보 조회 결과</title>
</head>
<body>
<script type="text/javascript"> 
function searchview(index){
	var form2 = document.form1;
	form2.gotoindex.value = index;
	//alert(form2.gotoindex.value);
	//return;
	form2.target = "_self";
	form2.action = " pr_search_property_result.jsp";
	form2.submit();
}

function tabs(idx){  
	return;
    for(i = 1; i <= 3 ; i++ ){   
        document.getElementById('tab'+i).className = "";   
        document.getElementById('content'+i).className = "content hide";   
    }   
    document.getElementById('tab'+idx).className = "active";   
    document.getElementById('content'+idx).className = "content show";   
}  
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="tb_option" value="<%=param_TB%>"/>
<input type="hidden" name="db_option" value="<%=param_DB%>"/>
<input type="hidden" name="part_1" value="<%=param_part1%>"/>
<input type="hidden" name="part_2" value="<%=param_part2%>"/>
<input type="hidden" name="mp_option" value="<%=param_MP%>"/>
<input type="hidden" name="srd_value" value="<%=param_srd%>"/>
<input type="hidden" name="gotoindex" value="<%=gotoindex%>"/>
<p class="level2">플라즈마 물성 검색 정보</p>
<span class="help">선택된 대분류, 데이터분류, 주프로세스에 따라 입자 검색 개수가 다르게 나타날 수 있습니다.</span><br>
<table class="tab2">
  <tr>
    <th class="thc">검색 조건</th>
  </tr>
  <tr>
    <td class="tdl"><%=search_exp%></td>
  </tr>
</table>
<div>  
    <div class='tabs-area'>  
        <ul class='tabs'>  
        <li> </li>  
        <li><a id='tab1' title="Heavy particle Impact" href="javascript:tabs('1');">Heavy particle Impact (<%=HI_COUNT %> 건)</a></li>  
        </ul>  
    </div>  
    <div class='tabs-line'></div>  
</div> 
<div id='content1' class='content show'> 
    <table class="tab2" width="650" height="650">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<tr height="40">
			<th class="thc4" colspan = 2>Scattering</th>
			<th class="thc4" colspan = 2>Ionization</th>
			<th class="thc4" colspan = 2>Excitation</th>
			<th class="thc4" colspan = 2>Recombination</th>
		</tr>
		<tr height="38">
			<td class="tdrbold">Total :</td>
			<%
				if(!HSCAT_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HSCAT_Total'); return false;"><%= HSCAT_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HSCAT_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!HIONI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Total'); return false;"><%= HIONI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!HEXCI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Total'); return false;"><%= HEXCI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!HRECO_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HRECO_Total'); return false;"><%= HRECO_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HRECO_Total%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Elastic :</td>
			<%
				if(!HSCAT_Elastic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HSCAT_Elastic'); return false;"><%= HSCAT_Elastic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HSCAT_Elastic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Single :</td>
			<%
				if(!HIONI_Single.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Single'); return false;"><%= HIONI_Single%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Single%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electronic :</td>
			<%
				if(!HEXCI_Electronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Electronic'); return false;"><%= HEXCI_Electronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Electronic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!HRECO_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HRECO_Radiative'); return false;"><%= HRECO_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HRECO_Radiative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Momentum transfer :</td>
			<%
				if(!HSCAT_Momentum_Transfer.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HSCAT_Momentum_Transfer'); return false;"><%= HSCAT_Momentum_Transfer%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HSCAT_Momentum_Transfer%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Double :</td>
			<%
				if(!HIONI_Double.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Double'); return false;"><%= HIONI_Double%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Double%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Vibrational :</td>
			<%
				if(!HEXCI_Vibrational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Vibrational'); return false;"><%= HEXCI_Vibrational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Vibrational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dielectronic :</td>
			<%
				if(!HRECO_Dielectronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HRECO_Dielectronic'); return false;"><%= HRECO_Dielectronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HRECO_Dielectronic%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Multiple :</td>
			<%
				if(!HIONI_Multiple.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Multiple'); return false;"><%= HIONI_Multiple%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Multiple%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Rotational :</td>
			<%
				if(!HEXCI_Rotational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Rotational'); return false;"><%= HEXCI_Rotational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Rotational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Three body :</td>
			<%
				if(!HRECO_Three_Body.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HRECO_Three_Body'); return false;"><%= HRECO_Three_Body%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HRECO_Three_Body%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Partial :</td>
			<%
				if(!HIONI_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Partial'); return false;"><%= HIONI_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!HEXCI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Associative'); return false;"><%= HEXCI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!HRECO_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HRECO_Dissociative'); return false;"><%= HRECO_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HRECO_Dissociative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Associative :</td>
			<%
				if(!HIONI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Associative'); return false;"><%= HIONI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!HEXCI_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Radiative'); return false;"><%= HEXCI_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Radiative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Penning :</td>
			<%
				if(!HIONI_Penning.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HIONI_Penning'); return false;"><%= HIONI_Penning%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HIONI_Penning%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!HEXCI_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HEXCI_Dissociative'); return false;"><%= HEXCI_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HEXCI_Dissociative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>		
		<tr height="40">			
			<th class="thc4" colspan = 2>Dissociation</th>
			<th class="thc4" colspan = 2>Attachment</th>
			<th class="thc4" colspan = 2>Reaction</th>
			<th class="thc4" colspan = 2></th>
		</tr>
		<tr height="38">		
			<td class="tdrbold">Total :</td>
			<%
				if(!HDISS_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HDISS_Total'); return false;"><%= HDISS_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HDISS_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!HATTA_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Total'); return false;"><%= HATTA_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electron loss :</td>
			<%
				if(!HREAC_Electron_Loss.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HREAC_Electron_Loss'); return false;"><%= HREAC_Electron_Loss%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HREAC_Electron_Loss%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold">Partial :</td>
			<%
				if(!HDISS_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HDISS_Partial'); return false;"><%= HDISS_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HDISS_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!HATTA_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Dissociative'); return false;"><%= HATTA_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Dissociative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Particle Interchange :</td>
			<%
				if(!HREAC_Particle_Interchange.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HREAC_Particle_Interchange'); return false;"><%= HREAC_Particle_Interchange%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HREAC_Particle_Interchange%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">			
			<td class="tdrbold">Total Neutral Fragments :</td>
			<%
				if(!HDISS_Total_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HDISS_Total_Neutral_Fragments'); return false;"><%= HDISS_Total_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HDISS_Total_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!HATTA_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Associative'); return false;"><%= HATTA_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">			
			<td class="tdrbold">Neutral Fragments :</td>
			<%
				if(!HDISS_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HDISS_Neutral_Fragments'); return false;"><%= HDISS_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HDISS_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!HATTA_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Radiative'); return false;"><%= HATTA_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Radiative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Thermal electron :</td>
			<%
				if(!HATTA_Thermal_Electron.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Thermal_Electron'); return false;"><%= HATTA_Thermal_Electron%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Thermal_Electron%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>		
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Total Dissociative :</td>
			<%
				if(!HATTA_Total_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('HATTA_Total_Dissociative'); return false;"><%= HATTA_Total_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= HATTA_Total_Dissociative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold"></td>
			<td class="tdl"></td>			
		</tr>
	</table>
</div> 
</form>
</body>
</html>