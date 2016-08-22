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

	//Photon Impact 관련 항목
	//Photon Impact - Scattering
	String PSCAT_Total = ComUtil.isNullToZeroString(search_info.getPSCAT_Total());
	String PSCAT_Elastic = ComUtil.isNullToZeroString(search_info.getPSCAT_Elastic());
	String PSCAT_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPSCAT_Momentum_Transfer());
	String PSCAT_Single = ComUtil.isNullToZeroString(search_info.getPSCAT_Single());
	String PSCAT_Double = ComUtil.isNullToZeroString(search_info.getPSCAT_Double());
	String PSCAT_Multiple = ComUtil.isNullToZeroString(search_info.getPSCAT_Multiple());
	String PSCAT_Partial = ComUtil.isNullToZeroString(search_info.getPSCAT_Partial());
	String PSCAT_Associative = ComUtil.isNullToZeroString(search_info.getPSCAT_Associative());
	String PSCAT_Penning = ComUtil.isNullToZeroString(search_info.getPSCAT_Penning());
	String PSCAT_Reaction = ComUtil.isNullToZeroString(search_info.getPSCAT_Reaction());
	String PSCAT_Electronic = ComUtil.isNullToZeroString(search_info.getPSCAT_Electronic());
	String PSCAT_Vibrational = ComUtil.isNullToZeroString(search_info.getPSCAT_Vibrational());
	String PSCAT_Rotational = ComUtil.isNullToZeroString(search_info.getPSCAT_Rotational());
	String PSCAT_Radiative = ComUtil.isNullToZeroString(search_info.getPSCAT_Radiative());
	String PSCAT_Dielectronic = ComUtil.isNullToZeroString(search_info.getPSCAT_Dielectronic());
	String PSCAT_Three_Body = ComUtil.isNullToZeroString(search_info.getPSCAT_Three_Body());
	String PSCAT_Dissociative = ComUtil.isNullToZeroString(search_info.getPSCAT_Dissociative());
	String PSCAT_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPSCAT_Transfer_Ionization());
	String PSCAT_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPSCAT_Thermal_Electron());
	String PSCAT_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPSCAT_Electron_Loss());
	String PSCAT_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPSCAT_Particle_Interchange());
	String PSCAT_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPSCAT_Total_Neutral_Fragments());
	String PSCAT_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPSCAT_Neutral_Fragments());
	String PSCAT_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPSCAT_Total_Dissociative());
	String PSCAT_Detachment = ComUtil.isNullToZeroString(search_info.getPSCAT_Detachment());
	String PSCAT_Autoionization = ComUtil.isNullToZeroString(search_info.getPSCAT_Autoionization());
	String PSCAT_Quenching = ComUtil.isNullToZeroString(search_info.getPSCAT_Quenching());
	String PSCAT_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPSCAT_X_ray_Production());
	String PSCAT_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPSCAT_Relacxation_Reaction());
	String PSCAT_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPSCAT_State_Selectivity());
	String PSCAT_Photon = ComUtil.isNullToZeroString(search_info.getPSCAT_Photon());
	String PSCAT_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPSCAT_Ion_Pair_Production());
	String PSCAT_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPSCAT_Charge_Transfer());
	String PSCAT_de_Excitation = ComUtil.isNullToZeroString(search_info.getPSCAT_de_Excitation());
	String PSCAT_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPSCAT_Neutral_Product_Dissociation());
	String PSCAT_Attachement = ComUtil.isNullToZeroString(search_info.getPSCAT_Attachement());
	String PSCAT_Inelastic = ComUtil.isNullToZeroString(search_info.getPSCAT_Inelastic());
	String PSCAT_Electron_Production = ComUtil.isNullToZeroString(search_info.getPSCAT_Electron_Production());
	String PSCAT_Vibrotational = ComUtil.isNullToZeroString(search_info.getPSCAT_Vibrotational());
	
	//Photon Impact - Ionization
	String PIONI_Total = ComUtil.isNullToZeroString(search_info.getPIONI_Total());
	String PIONI_Elastic = ComUtil.isNullToZeroString(search_info.getPIONI_Elastic());
	String PIONI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPIONI_Momentum_Transfer());
	String PIONI_Single = ComUtil.isNullToZeroString(search_info.getPIONI_Single());
	String PIONI_Double = ComUtil.isNullToZeroString(search_info.getPIONI_Double());
	String PIONI_Multiple = ComUtil.isNullToZeroString(search_info.getPIONI_Multiple());
	String PIONI_Partial = ComUtil.isNullToZeroString(search_info.getPIONI_Partial());
	String PIONI_Associative = ComUtil.isNullToZeroString(search_info.getPIONI_Associative());
	String PIONI_Penning = ComUtil.isNullToZeroString(search_info.getPIONI_Penning());
	String PIONI_Reaction = ComUtil.isNullToZeroString(search_info.getPIONI_Reaction());
	String PIONI_Electronic = ComUtil.isNullToZeroString(search_info.getPIONI_Electronic());
	String PIONI_Vibrational = ComUtil.isNullToZeroString(search_info.getPIONI_Vibrational());
	String PIONI_Rotational = ComUtil.isNullToZeroString(search_info.getPIONI_Rotational());
	String PIONI_Radiative = ComUtil.isNullToZeroString(search_info.getPIONI_Radiative());
	String PIONI_Dielectronic = ComUtil.isNullToZeroString(search_info.getPIONI_Dielectronic());
	String PIONI_Three_Body = ComUtil.isNullToZeroString(search_info.getPIONI_Three_Body());
	String PIONI_Dissociative = ComUtil.isNullToZeroString(search_info.getPIONI_Dissociative());
	String PIONI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPIONI_Transfer_Ionization());
	String PIONI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPIONI_Thermal_Electron());
	String PIONI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPIONI_Electron_Loss());
	String PIONI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPIONI_Particle_Interchange());
	String PIONI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPIONI_Total_Neutral_Fragments());
	String PIONI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPIONI_Neutral_Fragments());
	String PIONI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPIONI_Total_Dissociative());
	String PIONI_Detachment = ComUtil.isNullToZeroString(search_info.getPIONI_Detachment());
	String PIONI_Autoionization = ComUtil.isNullToZeroString(search_info.getPIONI_Autoionization());
	String PIONI_Quenching = ComUtil.isNullToZeroString(search_info.getPIONI_Quenching());
	String PIONI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPIONI_X_ray_Production());
	String PIONI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPIONI_Relacxation_Reaction());
	String PIONI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPIONI_State_Selectivity());
	String PIONI_Photon = ComUtil.isNullToZeroString(search_info.getPIONI_Photon());
	String PIONI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPIONI_Ion_Pair_Production());
	String PIONI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPIONI_Charge_Transfer());
	String PIONI_de_Excitation = ComUtil.isNullToZeroString(search_info.getPIONI_de_Excitation());
	String PIONI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPIONI_Neutral_Product_Dissociation());
	String PIONI_Attachement = ComUtil.isNullToZeroString(search_info.getPIONI_Attachement());
	String PIONI_Inelastic = ComUtil.isNullToZeroString(search_info.getPIONI_Inelastic());
	String PIONI_Electron_Production = ComUtil.isNullToZeroString(search_info.getPIONI_Electron_Production());
	String PIONI_Vibrotational = ComUtil.isNullToZeroString(search_info.getPIONI_Vibrotational());
	
	//Photon Impact - Excitation
	String PEXCI_Total = ComUtil.isNullToZeroString(search_info.getPEXCI_Total());
	String PEXCI_Elastic = ComUtil.isNullToZeroString(search_info.getPEXCI_Elastic());
	String PEXCI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPEXCI_Momentum_Transfer());
	String PEXCI_Single = ComUtil.isNullToZeroString(search_info.getPEXCI_Single());
	String PEXCI_Double = ComUtil.isNullToZeroString(search_info.getPEXCI_Double());
	String PEXCI_Multiple = ComUtil.isNullToZeroString(search_info.getPEXCI_Multiple());
	String PEXCI_Partial = ComUtil.isNullToZeroString(search_info.getPEXCI_Partial());
	String PEXCI_Associative = ComUtil.isNullToZeroString(search_info.getPEXCI_Associative());
	String PEXCI_Penning = ComUtil.isNullToZeroString(search_info.getPEXCI_Penning());
	String PEXCI_Reaction = ComUtil.isNullToZeroString(search_info.getPEXCI_Reaction());
	String PEXCI_Electronic = ComUtil.isNullToZeroString(search_info.getPEXCI_Electronic());
	String PEXCI_Vibrational = ComUtil.isNullToZeroString(search_info.getPEXCI_Vibrational());
	String PEXCI_Rotational = ComUtil.isNullToZeroString(search_info.getPEXCI_Rotational());
	String PEXCI_Radiative = ComUtil.isNullToZeroString(search_info.getPEXCI_Radiative());
	String PEXCI_Dielectronic = ComUtil.isNullToZeroString(search_info.getPEXCI_Dielectronic());
	String PEXCI_Three_Body = ComUtil.isNullToZeroString(search_info.getPEXCI_Three_Body());
	String PEXCI_Dissociative = ComUtil.isNullToZeroString(search_info.getPEXCI_Dissociative());
	String PEXCI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPEXCI_Transfer_Ionization());
	String PEXCI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPEXCI_Thermal_Electron());
	String PEXCI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPEXCI_Electron_Loss());
	String PEXCI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPEXCI_Particle_Interchange());
	String PEXCI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPEXCI_Total_Neutral_Fragments());
	String PEXCI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPEXCI_Neutral_Fragments());
	String PEXCI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPEXCI_Total_Dissociative());
	String PEXCI_Detachment = ComUtil.isNullToZeroString(search_info.getPEXCI_Detachment());
	String PEXCI_Autoionization = ComUtil.isNullToZeroString(search_info.getPEXCI_Autoionization());
	String PEXCI_Quenching = ComUtil.isNullToZeroString(search_info.getPEXCI_Quenching());
	String PEXCI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPEXCI_X_ray_Production());
	String PEXCI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPEXCI_Relacxation_Reaction());
	String PEXCI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPEXCI_State_Selectivity());
	String PEXCI_Photon = ComUtil.isNullToZeroString(search_info.getPEXCI_Photon());
	String PEXCI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPEXCI_Ion_Pair_Production());
	String PEXCI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPEXCI_Charge_Transfer());
	String PEXCI_de_Excitation = ComUtil.isNullToZeroString(search_info.getPEXCI_de_Excitation());
	String PEXCI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPEXCI_Neutral_Product_Dissociation());
	String PEXCI_Attachement = ComUtil.isNullToZeroString(search_info.getPEXCI_Attachement());
	String PEXCI_Inelastic = ComUtil.isNullToZeroString(search_info.getPEXCI_Inelastic());
	String PEXCI_Electron_Production = ComUtil.isNullToZeroString(search_info.getPEXCI_Electron_Production());
	String PEXCI_Vibrotational = ComUtil.isNullToZeroString(search_info.getPEXCI_Vibrotational());
	
	//Photon Impact - Recombination
	String PRECO_Total = ComUtil.isNullToZeroString(search_info.getPRECO_Total());
	String PRECO_Elastic = ComUtil.isNullToZeroString(search_info.getPRECO_Elastic());
	String PRECO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPRECO_Momentum_Transfer());
	String PRECO_Single = ComUtil.isNullToZeroString(search_info.getPRECO_Single());
	String PRECO_Double = ComUtil.isNullToZeroString(search_info.getPRECO_Double());
	String PRECO_Multiple = ComUtil.isNullToZeroString(search_info.getPRECO_Multiple());
	String PRECO_Partial = ComUtil.isNullToZeroString(search_info.getPRECO_Partial());
	String PRECO_Associative = ComUtil.isNullToZeroString(search_info.getPRECO_Associative());
	String PRECO_Penning = ComUtil.isNullToZeroString(search_info.getPRECO_Penning());
	String PRECO_Reaction = ComUtil.isNullToZeroString(search_info.getPRECO_Reaction());
	String PRECO_Electronic = ComUtil.isNullToZeroString(search_info.getPRECO_Electronic());
	String PRECO_Vibrational = ComUtil.isNullToZeroString(search_info.getPRECO_Vibrational());
	String PRECO_Rotational = ComUtil.isNullToZeroString(search_info.getPRECO_Rotational());
	String PRECO_Radiative = ComUtil.isNullToZeroString(search_info.getPRECO_Radiative());
	String PRECO_Dielectronic = ComUtil.isNullToZeroString(search_info.getPRECO_Dielectronic());
	String PRECO_Three_Body = ComUtil.isNullToZeroString(search_info.getPRECO_Three_Body());
	String PRECO_Dissociative = ComUtil.isNullToZeroString(search_info.getPRECO_Dissociative());
	String PRECO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPRECO_Transfer_Ionization());
	String PRECO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPRECO_Thermal_Electron());
	String PRECO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPRECO_Electron_Loss());
	String PRECO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPRECO_Particle_Interchange());
	String PRECO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPRECO_Total_Neutral_Fragments());
	String PRECO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPRECO_Neutral_Fragments());
	String PRECO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPRECO_Total_Dissociative());
	String PRECO_Detachment = ComUtil.isNullToZeroString(search_info.getPRECO_Detachment());
	String PRECO_Autoionization = ComUtil.isNullToZeroString(search_info.getPRECO_Autoionization());
	String PRECO_Quenching = ComUtil.isNullToZeroString(search_info.getPRECO_Quenching());
	String PRECO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPRECO_X_ray_Production());
	String PRECO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPRECO_Relacxation_Reaction());
	String PRECO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPRECO_State_Selectivity());
	String PRECO_Photon = ComUtil.isNullToZeroString(search_info.getPRECO_Photon());
	String PRECO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPRECO_Ion_Pair_Production());
	String PRECO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPRECO_Charge_Transfer());
	String PRECO_de_Excitation = ComUtil.isNullToZeroString(search_info.getPRECO_de_Excitation());
	String PRECO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPRECO_Neutral_Product_Dissociation());
	String PRECO_Attachement = ComUtil.isNullToZeroString(search_info.getPRECO_Attachement());
	String PRECO_Inelastic = ComUtil.isNullToZeroString(search_info.getPRECO_Inelastic());
	String PRECO_Electron_Production = ComUtil.isNullToZeroString(search_info.getPRECO_Electron_Production());
	String PRECO_Vibrotational = ComUtil.isNullToZeroString(search_info.getPRECO_Vibrotational());           
	
	
	//Photon Impact - Charge Transfer
	String PCHTR_Total = ComUtil.isNullToZeroString(search_info.getPCHTR_Total());
	String PCHTR_Elastic = ComUtil.isNullToZeroString(search_info.getPCHTR_Elastic());
	String PCHTR_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPCHTR_Momentum_Transfer());
	String PCHTR_Single = ComUtil.isNullToZeroString(search_info.getPCHTR_Single());
	String PCHTR_Double = ComUtil.isNullToZeroString(search_info.getPCHTR_Double());
	String PCHTR_Multiple = ComUtil.isNullToZeroString(search_info.getPCHTR_Multiple());
	String PCHTR_Partial = ComUtil.isNullToZeroString(search_info.getPCHTR_Partial());
	String PCHTR_Associative = ComUtil.isNullToZeroString(search_info.getPCHTR_Associative());
	String PCHTR_Penning = ComUtil.isNullToZeroString(search_info.getPCHTR_Penning());
	String PCHTR_Reaction = ComUtil.isNullToZeroString(search_info.getPCHTR_Reaction());
	String PCHTR_Electronic = ComUtil.isNullToZeroString(search_info.getPCHTR_Electronic());
	String PCHTR_Vibrational = ComUtil.isNullToZeroString(search_info.getPCHTR_Vibrational());
	String PCHTR_Rotational = ComUtil.isNullToZeroString(search_info.getPCHTR_Rotational());
	String PCHTR_Radiative = ComUtil.isNullToZeroString(search_info.getPCHTR_Radiative());
	String PCHTR_Dielectronic = ComUtil.isNullToZeroString(search_info.getPCHTR_Dielectronic());
	String PCHTR_Three_Body = ComUtil.isNullToZeroString(search_info.getPCHTR_Three_Body());
	String PCHTR_Dissociative = ComUtil.isNullToZeroString(search_info.getPCHTR_Dissociative());
	String PCHTR_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPCHTR_Transfer_Ionization());
	String PCHTR_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPCHTR_Thermal_Electron());
	String PCHTR_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPCHTR_Electron_Loss());
	String PCHTR_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPCHTR_Particle_Interchange());
	String PCHTR_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPCHTR_Total_Neutral_Fragments());
	String PCHTR_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPCHTR_Neutral_Fragments());
	String PCHTR_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPCHTR_Total_Dissociative());
	String PCHTR_Detachment = ComUtil.isNullToZeroString(search_info.getPCHTR_Detachment());
	String PCHTR_Autoionization = ComUtil.isNullToZeroString(search_info.getPCHTR_Autoionization());
	String PCHTR_Quenching = ComUtil.isNullToZeroString(search_info.getPCHTR_Quenching());
	String PCHTR_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPCHTR_X_ray_Production());
	String PCHTR_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPCHTR_Relacxation_Reaction());
	String PCHTR_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPCHTR_State_Selectivity());
	String PCHTR_Photon = ComUtil.isNullToZeroString(search_info.getPCHTR_Photon());
	String PCHTR_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPCHTR_Ion_Pair_Production());
	String PCHTR_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPCHTR_Charge_Transfer());
	String PCHTR_de_Excitation = ComUtil.isNullToZeroString(search_info.getPCHTR_de_Excitation());
	String PCHTR_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPCHTR_Neutral_Product_Dissociation());
	String PCHTR_Attachement = ComUtil.isNullToZeroString(search_info.getPCHTR_Attachement());
	String PCHTR_Inelastic = ComUtil.isNullToZeroString(search_info.getPCHTR_Inelastic());
	String PCHTR_Electron_Production = ComUtil.isNullToZeroString(search_info.getPCHTR_Electron_Production());
	String PCHTR_Vibrotational = ComUtil.isNullToZeroString(search_info.getPCHTR_Vibrotational());
	
	//Photon Impact - Dissociation
	String PDISS_Total = ComUtil.isNullToZeroString(search_info.getPDISS_Total());
	String PDISS_Elastic = ComUtil.isNullToZeroString(search_info.getPDISS_Elastic());
	String PDISS_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPDISS_Momentum_Transfer());
	String PDISS_Single = ComUtil.isNullToZeroString(search_info.getPDISS_Single());
	String PDISS_Double = ComUtil.isNullToZeroString(search_info.getPDISS_Double());
	String PDISS_Multiple = ComUtil.isNullToZeroString(search_info.getPDISS_Multiple());
	String PDISS_Partial = ComUtil.isNullToZeroString(search_info.getPDISS_Partial());
	String PDISS_Associative = ComUtil.isNullToZeroString(search_info.getPDISS_Associative());
	String PDISS_Penning = ComUtil.isNullToZeroString(search_info.getPDISS_Penning());
	String PDISS_Reaction = ComUtil.isNullToZeroString(search_info.getPDISS_Reaction());
	String PDISS_Electronic = ComUtil.isNullToZeroString(search_info.getPDISS_Electronic());
	String PDISS_Vibrational = ComUtil.isNullToZeroString(search_info.getPDISS_Vibrational());
	String PDISS_Rotational = ComUtil.isNullToZeroString(search_info.getPDISS_Rotational());
	String PDISS_Radiative = ComUtil.isNullToZeroString(search_info.getPDISS_Radiative());
	String PDISS_Dielectronic = ComUtil.isNullToZeroString(search_info.getPDISS_Dielectronic());
	String PDISS_Three_Body = ComUtil.isNullToZeroString(search_info.getPDISS_Three_Body());
	String PDISS_Dissociative = ComUtil.isNullToZeroString(search_info.getPDISS_Dissociative());
	String PDISS_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPDISS_Transfer_Ionization());
	String PDISS_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPDISS_Thermal_Electron());
	String PDISS_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPDISS_Electron_Loss());
	String PDISS_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPDISS_Particle_Interchange());
	String PDISS_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPDISS_Total_Neutral_Fragments());
	String PDISS_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPDISS_Neutral_Fragments());
	String PDISS_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPDISS_Total_Dissociative());
	String PDISS_Detachment = ComUtil.isNullToZeroString(search_info.getPDISS_Detachment());
	String PDISS_Autoionization = ComUtil.isNullToZeroString(search_info.getPDISS_Autoionization());
	String PDISS_Quenching = ComUtil.isNullToZeroString(search_info.getPDISS_Quenching());
	String PDISS_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPDISS_X_ray_Production());
	String PDISS_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPDISS_Relacxation_Reaction());
	String PDISS_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPDISS_State_Selectivity());
	String PDISS_Photon = ComUtil.isNullToZeroString(search_info.getPDISS_Photon());
	String PDISS_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPDISS_Ion_Pair_Production());
	String PDISS_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPDISS_Charge_Transfer());
	String PDISS_de_Excitation = ComUtil.isNullToZeroString(search_info.getPDISS_de_Excitation());
	String PDISS_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPDISS_Neutral_Product_Dissociation());
	String PDISS_Attachement = ComUtil.isNullToZeroString(search_info.getPDISS_Attachement());
	String PDISS_Inelastic = ComUtil.isNullToZeroString(search_info.getPDISS_Inelastic());
	String PDISS_Electron_Production = ComUtil.isNullToZeroString(search_info.getPDISS_Electron_Production());
	String PDISS_Vibrotational = ComUtil.isNullToZeroString(search_info.getPDISS_Vibrotational());    
	
	//Photon Impact - Attachment
	String PATTA_Total = ComUtil.isNullToZeroString(search_info.getPATTA_Total());
	String PATTA_Elastic = ComUtil.isNullToZeroString(search_info.getPATTA_Elastic());
	String PATTA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPATTA_Momentum_Transfer());
	String PATTA_Single = ComUtil.isNullToZeroString(search_info.getPATTA_Single());
	String PATTA_Double = ComUtil.isNullToZeroString(search_info.getPATTA_Double());
	String PATTA_Multiple = ComUtil.isNullToZeroString(search_info.getPATTA_Multiple());
	String PATTA_Partial = ComUtil.isNullToZeroString(search_info.getPATTA_Partial());
	String PATTA_Associative = ComUtil.isNullToZeroString(search_info.getPATTA_Associative());
	String PATTA_Penning = ComUtil.isNullToZeroString(search_info.getPATTA_Penning());
	String PATTA_Reaction = ComUtil.isNullToZeroString(search_info.getPATTA_Reaction());
	String PATTA_Electronic = ComUtil.isNullToZeroString(search_info.getPATTA_Electronic());
	String PATTA_Vibrational = ComUtil.isNullToZeroString(search_info.getPATTA_Vibrational());
	String PATTA_Rotational = ComUtil.isNullToZeroString(search_info.getPATTA_Rotational());
	String PATTA_Radiative = ComUtil.isNullToZeroString(search_info.getPATTA_Radiative());
	String PATTA_Dielectronic = ComUtil.isNullToZeroString(search_info.getPATTA_Dielectronic());
	String PATTA_Three_Body = ComUtil.isNullToZeroString(search_info.getPATTA_Three_Body());
	String PATTA_Dissociative = ComUtil.isNullToZeroString(search_info.getPATTA_Dissociative());
	String PATTA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPATTA_Transfer_Ionization());
	String PATTA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPATTA_Thermal_Electron());
	String PATTA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPATTA_Electron_Loss());
	String PATTA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPATTA_Particle_Interchange());
	String PATTA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPATTA_Total_Neutral_Fragments());
	String PATTA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPATTA_Neutral_Fragments());
	String PATTA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPATTA_Total_Dissociative());
	String PATTA_Detachment = ComUtil.isNullToZeroString(search_info.getPATTA_Detachment());
	String PATTA_Autoionization = ComUtil.isNullToZeroString(search_info.getPATTA_Autoionization());
	String PATTA_Quenching = ComUtil.isNullToZeroString(search_info.getPATTA_Quenching());
	String PATTA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPATTA_X_ray_Production());
	String PATTA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPATTA_Relacxation_Reaction());
	String PATTA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPATTA_State_Selectivity());
	String PATTA_Photon = ComUtil.isNullToZeroString(search_info.getPATTA_Photon());
	String PATTA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPATTA_Ion_Pair_Production());
	String PATTA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPATTA_Charge_Transfer());
	String PATTA_de_Excitation = ComUtil.isNullToZeroString(search_info.getPATTA_de_Excitation());
	String PATTA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPATTA_Neutral_Product_Dissociation());
	String PATTA_Attachement = ComUtil.isNullToZeroString(search_info.getPATTA_Attachement());
	String PATTA_Inelastic = ComUtil.isNullToZeroString(search_info.getPATTA_Inelastic());
	String PATTA_Electron_Production = ComUtil.isNullToZeroString(search_info.getPATTA_Electron_Production());
	String PATTA_Vibrotational = ComUtil.isNullToZeroString(search_info.getPATTA_Vibrotational());     
	
	//Photon Impact - Reaction
	String PREAC_Total = ComUtil.isNullToZeroString(search_info.getPREAC_Total());
	String PREAC_Elastic = ComUtil.isNullToZeroString(search_info.getPREAC_Elastic());
	String PREAC_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPREAC_Momentum_Transfer());
	String PREAC_Single = ComUtil.isNullToZeroString(search_info.getPREAC_Single());
	String PREAC_Double = ComUtil.isNullToZeroString(search_info.getPREAC_Double());
	String PREAC_Multiple = ComUtil.isNullToZeroString(search_info.getPREAC_Multiple());
	String PREAC_Partial = ComUtil.isNullToZeroString(search_info.getPREAC_Partial());
	String PREAC_Associative = ComUtil.isNullToZeroString(search_info.getPREAC_Associative());
	String PREAC_Penning = ComUtil.isNullToZeroString(search_info.getPREAC_Penning());
	String PREAC_Reaction = ComUtil.isNullToZeroString(search_info.getPREAC_Reaction());
	String PREAC_Electronic = ComUtil.isNullToZeroString(search_info.getPREAC_Electronic());
	String PREAC_Vibrational = ComUtil.isNullToZeroString(search_info.getPREAC_Vibrational());
	String PREAC_Rotational = ComUtil.isNullToZeroString(search_info.getPREAC_Rotational());
	String PREAC_Radiative = ComUtil.isNullToZeroString(search_info.getPREAC_Radiative());
	String PREAC_Dielectronic = ComUtil.isNullToZeroString(search_info.getPREAC_Dielectronic());
	String PREAC_Three_Body = ComUtil.isNullToZeroString(search_info.getPREAC_Three_Body());
	String PREAC_Dissociative = ComUtil.isNullToZeroString(search_info.getPREAC_Dissociative());
	String PREAC_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPREAC_Transfer_Ionization());
	String PREAC_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPREAC_Thermal_Electron());
	String PREAC_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPREAC_Electron_Loss());
	String PREAC_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPREAC_Particle_Interchange());
	String PREAC_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPREAC_Total_Neutral_Fragments());
	String PREAC_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPREAC_Neutral_Fragments());
	String PREAC_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPREAC_Total_Dissociative());
	String PREAC_Detachment = ComUtil.isNullToZeroString(search_info.getPREAC_Detachment());
	String PREAC_Autoionization = ComUtil.isNullToZeroString(search_info.getPREAC_Autoionization());
	String PREAC_Quenching = ComUtil.isNullToZeroString(search_info.getPREAC_Quenching());
	String PREAC_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPREAC_X_ray_Production());
	String PREAC_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPREAC_Relacxation_Reaction());
	String PREAC_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPREAC_State_Selectivity());
	String PREAC_Photon = ComUtil.isNullToZeroString(search_info.getPREAC_Photon());
	String PREAC_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPREAC_Ion_Pair_Production());
	String PREAC_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPREAC_Charge_Transfer());
	String PREAC_de_Excitation = ComUtil.isNullToZeroString(search_info.getPREAC_de_Excitation());
	String PREAC_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPREAC_Neutral_Product_Dissociation());
	String PREAC_Attachement = ComUtil.isNullToZeroString(search_info.getPREAC_Attachement());
	String PREAC_Inelastic = ComUtil.isNullToZeroString(search_info.getPREAC_Inelastic());
	String PREAC_Electron_Production = ComUtil.isNullToZeroString(search_info.getPREAC_Electron_Production());
	String PREAC_Vibrotational = ComUtil.isNullToZeroString(search_info.getPREAC_Vibrotational());  
	
	//Photon Impact - Detachment     
	String PDETA_Total = ComUtil.isNullToZeroString(search_info.getPDETA_Total());
	String PDETA_Elastic = ComUtil.isNullToZeroString(search_info.getPDETA_Elastic());
	String PDETA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPDETA_Momentum_Transfer());
	String PDETA_Single = ComUtil.isNullToZeroString(search_info.getPDETA_Single());
	String PDETA_Double = ComUtil.isNullToZeroString(search_info.getPDETA_Double());
	String PDETA_Multiple = ComUtil.isNullToZeroString(search_info.getPDETA_Multiple());
	String PDETA_Partial = ComUtil.isNullToZeroString(search_info.getPDETA_Partial());
	String PDETA_Associative = ComUtil.isNullToZeroString(search_info.getPDETA_Associative());
	String PDETA_Penning = ComUtil.isNullToZeroString(search_info.getPDETA_Penning());
	String PDETA_Reaction = ComUtil.isNullToZeroString(search_info.getPDETA_Reaction());
	String PDETA_Electronic = ComUtil.isNullToZeroString(search_info.getPDETA_Electronic());
	String PDETA_Vibrational = ComUtil.isNullToZeroString(search_info.getPDETA_Vibrational());
	String PDETA_Rotational = ComUtil.isNullToZeroString(search_info.getPDETA_Rotational());
	String PDETA_Radiative = ComUtil.isNullToZeroString(search_info.getPDETA_Radiative());
	String PDETA_Dielectronic = ComUtil.isNullToZeroString(search_info.getPDETA_Dielectronic());
	String PDETA_Three_Body = ComUtil.isNullToZeroString(search_info.getPDETA_Three_Body());
	String PDETA_Dissociative = ComUtil.isNullToZeroString(search_info.getPDETA_Dissociative());
	String PDETA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPDETA_Transfer_Ionization());
	String PDETA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPDETA_Thermal_Electron());
	String PDETA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPDETA_Electron_Loss());
	String PDETA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPDETA_Particle_Interchange());
	String PDETA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPDETA_Total_Neutral_Fragments());
	String PDETA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPDETA_Neutral_Fragments());
	String PDETA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPDETA_Total_Dissociative());
	String PDETA_Detachment = ComUtil.isNullToZeroString(search_info.getPDETA_Detachment());
	String PDETA_Autoionization = ComUtil.isNullToZeroString(search_info.getPDETA_Autoionization());
	String PDETA_Quenching = ComUtil.isNullToZeroString(search_info.getPDETA_Quenching());
	String PDETA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPDETA_X_ray_Production());
	String PDETA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPDETA_Relacxation_Reaction());
	String PDETA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPDETA_State_Selectivity());
	String PDETA_Photon = ComUtil.isNullToZeroString(search_info.getPDETA_Photon());
	String PDETA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPDETA_Ion_Pair_Production());
	String PDETA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPDETA_Charge_Transfer());
	String PDETA_de_Excitation = ComUtil.isNullToZeroString(search_info.getPDETA_de_Excitation());
	String PDETA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPDETA_Neutral_Product_Dissociation());
	String PDETA_Attachement = ComUtil.isNullToZeroString(search_info.getPDETA_Attachement());
	String PDETA_Inelastic = ComUtil.isNullToZeroString(search_info.getPDETA_Inelastic());
	String PDETA_Electron_Production = ComUtil.isNullToZeroString(search_info.getPDETA_Electron_Production());
	String PDETA_Vibrotational = ComUtil.isNullToZeroString(search_info.getPDETA_Vibrotational()); 
	
	//Photon Impact - Absorption 
	String PABSO_Total = ComUtil.isNullToZeroString(search_info.getPABSO_Total());
	String PABSO_Elastic = ComUtil.isNullToZeroString(search_info.getPABSO_Elastic());
	String PABSO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getPABSO_Momentum_Transfer());
	String PABSO_Single = ComUtil.isNullToZeroString(search_info.getPABSO_Single());
	String PABSO_Double = ComUtil.isNullToZeroString(search_info.getPABSO_Double());
	String PABSO_Multiple = ComUtil.isNullToZeroString(search_info.getPABSO_Multiple());
	String PABSO_Partial = ComUtil.isNullToZeroString(search_info.getPABSO_Partial());
	String PABSO_Associative = ComUtil.isNullToZeroString(search_info.getPABSO_Associative());
	String PABSO_Penning = ComUtil.isNullToZeroString(search_info.getPABSO_Penning());
	String PABSO_Reaction = ComUtil.isNullToZeroString(search_info.getPABSO_Reaction());
	String PABSO_Electronic = ComUtil.isNullToZeroString(search_info.getPABSO_Electronic());
	String PABSO_Vibrational = ComUtil.isNullToZeroString(search_info.getPABSO_Vibrational());
	String PABSO_Rotational = ComUtil.isNullToZeroString(search_info.getPABSO_Rotational());
	String PABSO_Radiative = ComUtil.isNullToZeroString(search_info.getPABSO_Radiative());
	String PABSO_Dielectronic = ComUtil.isNullToZeroString(search_info.getPABSO_Dielectronic());
	String PABSO_Three_Body = ComUtil.isNullToZeroString(search_info.getPABSO_Three_Body());
	String PABSO_Dissociative = ComUtil.isNullToZeroString(search_info.getPABSO_Dissociative());
	String PABSO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getPABSO_Transfer_Ionization());
	String PABSO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getPABSO_Thermal_Electron());
	String PABSO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getPABSO_Electron_Loss());
	String PABSO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getPABSO_Particle_Interchange());
	String PABSO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPABSO_Total_Neutral_Fragments());
	String PABSO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getPABSO_Neutral_Fragments());
	String PABSO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getPABSO_Total_Dissociative());
	String PABSO_Detachment = ComUtil.isNullToZeroString(search_info.getPABSO_Detachment());
	String PABSO_Autoionization = ComUtil.isNullToZeroString(search_info.getPABSO_Autoionization());
	String PABSO_Quenching = ComUtil.isNullToZeroString(search_info.getPABSO_Quenching());
	String PABSO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getPABSO_X_ray_Production());
	String PABSO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getPABSO_Relacxation_Reaction());
	String PABSO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getPABSO_State_Selectivity());
	String PABSO_Photon = ComUtil.isNullToZeroString(search_info.getPABSO_Photon());
	String PABSO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getPABSO_Ion_Pair_Production());
	String PABSO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getPABSO_Charge_Transfer());
	String PABSO_de_Excitation = ComUtil.isNullToZeroString(search_info.getPABSO_de_Excitation());
	String PABSO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getPABSO_Neutral_Product_Dissociation());
	String PABSO_Attachement = ComUtil.isNullToZeroString(search_info.getPABSO_Attachement());
	String PABSO_Inelastic = ComUtil.isNullToZeroString(search_info.getPABSO_Inelastic());
	String PABSO_Electron_Production = ComUtil.isNullToZeroString(search_info.getPABSO_Electron_Production());
	String PABSO_Vibrotational = ComUtil.isNullToZeroString(search_info.getPABSO_Vibrotational()); 
	
	String PI_COUNT = ComUtil.isNullToZeroString(search_info.getP_COUNT());
	
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
        <li><a id='tab1' title="Photon Impact" href="javascript:tabs('1');">Photon Impact (<%=PI_COUNT %> 건)</a></li>  
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
				if(!PSCAT_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PSCAT_Total'); return false;"><%= PSCAT_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PSCAT_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!PIONI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Total'); return false;"><%= PIONI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!PEXCI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Total'); return false;"><%= PEXCI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!PRECO_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PRECO_Total'); return false;"><%= PRECO_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PRECO_Total%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Elastic :</td>
			<%
				if(!PSCAT_Elastic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PSCAT_Elastic'); return false;"><%= PSCAT_Elastic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PSCAT_Elastic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Single :</td>
			<%
				if(!PIONI_Single.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Single'); return false;"><%= PIONI_Single%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Single%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electronic :</td>
			<%
				if(!PEXCI_Electronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Electronic'); return false;"><%= PEXCI_Electronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Electronic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!PRECO_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PRECO_Radiative'); return false;"><%= PRECO_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PRECO_Radiative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Momentum transfer :</td>
			<%
				if(!PSCAT_Momentum_Transfer.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PSCAT_Momentum_Transfer'); return false;"><%= PSCAT_Momentum_Transfer%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PSCAT_Momentum_Transfer%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Double :</td>
			<%
				if(!PIONI_Double.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Double'); return false;"><%= PIONI_Double%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Double%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Vibrational :</td>
			<%
				if(!PEXCI_Vibrational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Vibrational'); return false;"><%= PEXCI_Vibrational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Vibrational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dielectronic :</td>
			<%
				if(!PRECO_Dielectronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PRECO_Dielectronic'); return false;"><%= PRECO_Dielectronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PRECO_Dielectronic%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Multiple :</td>
			<%
				if(!PIONI_Multiple.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Multiple'); return false;"><%= PIONI_Multiple%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Multiple%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Rotational :</td>
			<%
				if(!PEXCI_Rotational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Rotational'); return false;"><%= PEXCI_Rotational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Rotational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Three body :</td>
			<%
				if(!PRECO_Three_Body.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PRECO_Three_Body'); return false;"><%= PRECO_Three_Body%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PRECO_Three_Body%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Partial :</td>
			<%
				if(!PIONI_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Partial'); return false;"><%= PIONI_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!PEXCI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Associative'); return false;"><%= PEXCI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!PRECO_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PRECO_Dissociative'); return false;"><%= PRECO_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PRECO_Dissociative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Associative :</td>
			<%
				if(!PIONI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Associative'); return false;"><%= PIONI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!PEXCI_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Radiative'); return false;"><%= PEXCI_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Radiative%> 건</td>
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
				if(!PIONI_Penning.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PIONI_Penning'); return false;"><%= PIONI_Penning%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PIONI_Penning%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!PEXCI_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PEXCI_Dissociative'); return false;"><%= PEXCI_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PEXCI_Dissociative%> 건</td>
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
				if(!PDISS_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PDISS_Total'); return false;"><%= PDISS_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PDISS_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!PATTA_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Total'); return false;"><%= PATTA_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electron loss :</td>
			<%
				if(!PREAC_Electron_Loss.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PREAC_Electron_Loss'); return false;"><%= PREAC_Electron_Loss%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PREAC_Electron_Loss%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold">Partial :</td>
			<%
				if(!PDISS_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PDISS_Partial'); return false;"><%= PDISS_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PDISS_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!PATTA_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Dissociative'); return false;"><%= PATTA_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Dissociative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Particle Interchange :</td>
			<%
				if(!PREAC_Particle_Interchange.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PREAC_Particle_Interchange'); return false;"><%= PREAC_Particle_Interchange%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PREAC_Particle_Interchange%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">			
			<td class="tdrbold">Total Neutral Fragments :</td>
			<%
				if(!PDISS_Total_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PDISS_Total_Neutral_Fragments'); return false;"><%= PDISS_Total_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PDISS_Total_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!PATTA_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Associative'); return false;"><%= PATTA_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Associative%> 건</td>
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
				if(!PDISS_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PDISS_Neutral_Fragments'); return false;"><%= PDISS_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PDISS_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!PATTA_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Radiative'); return false;"><%= PATTA_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Radiative%> 건</td>
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
				if(!PATTA_Thermal_Electron.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Thermal_Electron'); return false;"><%= PATTA_Thermal_Electron%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Thermal_Electron%> 건</td>
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
				if(!PATTA_Total_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('PATTA_Total_Dissociative'); return false;"><%= PATTA_Total_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= PATTA_Total_Dissociative%> 건</td>
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