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
	
	String st_param_TB = codeControl.getExpName(param_TB);
	String st_param_DB = codeControl.getExpName(param_DB);
	String st_param_MP = "-";
	if(!param_MP.equalsIgnoreCase("MP_00")){
		st_param_MP = codeControl.getExpName(param_MP);
	}
	
	//Electron Impact 관련 항목
	//Electron Impact - Scattering
	String ESCAT_Total = ComUtil.isNullToZeroString(search_info.getESCAT_Total());
	String ESCAT_Elastic = ComUtil.isNullToZeroString(search_info.getESCAT_Elastic());
	String ESCAT_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getESCAT_Momentum_Transfer());
	String ESCAT_Single = ComUtil.isNullToZeroString(search_info.getESCAT_Single());
	String ESCAT_Double = ComUtil.isNullToZeroString(search_info.getESCAT_Double());
	String ESCAT_Multiple = ComUtil.isNullToZeroString(search_info.getESCAT_Multiple());
	String ESCAT_Partial = ComUtil.isNullToZeroString(search_info.getESCAT_Partial());
	String ESCAT_Associative = ComUtil.isNullToZeroString(search_info.getESCAT_Associative());
	String ESCAT_Penning = ComUtil.isNullToZeroString(search_info.getESCAT_Penning());
	String ESCAT_Reaction = ComUtil.isNullToZeroString(search_info.getESCAT_Reaction());
	String ESCAT_Electronic = ComUtil.isNullToZeroString(search_info.getESCAT_Electronic());
	String ESCAT_Vibrational = ComUtil.isNullToZeroString(search_info.getESCAT_Vibrational());
	String ESCAT_Rotational = ComUtil.isNullToZeroString(search_info.getESCAT_Rotational());
	String ESCAT_Radiative = ComUtil.isNullToZeroString(search_info.getESCAT_Radiative());
	String ESCAT_Dielectronic = ComUtil.isNullToZeroString(search_info.getESCAT_Dielectronic());
	String ESCAT_Three_Body = ComUtil.isNullToZeroString(search_info.getESCAT_Three_Body());
	String ESCAT_Dissociative = ComUtil.isNullToZeroString(search_info.getESCAT_Dissociative());
	String ESCAT_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getESCAT_Transfer_Ionization());
	String ESCAT_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getESCAT_Thermal_Electron());
	String ESCAT_Electron_Loss = ComUtil.isNullToZeroString(search_info.getESCAT_Electron_Loss());
	String ESCAT_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getESCAT_Particle_Interchange());
	String ESCAT_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getESCAT_Total_Neutral_Fragments());
	String ESCAT_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getESCAT_Neutral_Fragments());
	String ESCAT_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getESCAT_Total_Dissociative());
	String ESCAT_Detachment = ComUtil.isNullToZeroString(search_info.getESCAT_Detachment());
	String ESCAT_Autoionization = ComUtil.isNullToZeroString(search_info.getESCAT_Autoionization());
	String ESCAT_Quenching = ComUtil.isNullToZeroString(search_info.getESCAT_Quenching());
	String ESCAT_X_ray_Production = ComUtil.isNullToZeroString(search_info.getESCAT_X_ray_Production());
	String ESCAT_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getESCAT_Relacxation_Reaction());
	String ESCAT_State_Selectivity = ComUtil.isNullToZeroString(search_info.getESCAT_State_Selectivity());
	String ESCAT_Photon = ComUtil.isNullToZeroString(search_info.getESCAT_Photon());
	String ESCAT_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getESCAT_Ion_Pair_Production());
	String ESCAT_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getESCAT_Charge_Transfer());
	String ESCAT_de_Excitation = ComUtil.isNullToZeroString(search_info.getESCAT_de_Excitation());
	String ESCAT_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getESCAT_Neutral_Product_Dissociation());
	String ESCAT_Attachement = ComUtil.isNullToZeroString(search_info.getESCAT_Attachement());
	String ESCAT_Inelastic = ComUtil.isNullToZeroString(search_info.getESCAT_Inelastic());
	String ESCAT_Electron_Production = ComUtil.isNullToZeroString(search_info.getESCAT_Electron_Production());
	String ESCAT_Vibrotational = ComUtil.isNullToZeroString(search_info.getESCAT_Vibrotational());
	
	//Electron Impact - Ionization
	String EIONI_Total = ComUtil.isNullToZeroString(search_info.getEIONI_Total());
	String EIONI_Elastic = ComUtil.isNullToZeroString(search_info.getEIONI_Elastic());
	String EIONI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEIONI_Momentum_Transfer());
	String EIONI_Single = ComUtil.isNullToZeroString(search_info.getEIONI_Single());
	String EIONI_Double = ComUtil.isNullToZeroString(search_info.getEIONI_Double());
	String EIONI_Multiple = ComUtil.isNullToZeroString(search_info.getEIONI_Multiple());
	String EIONI_Partial = ComUtil.isNullToZeroString(search_info.getEIONI_Partial());
	String EIONI_Associative = ComUtil.isNullToZeroString(search_info.getEIONI_Associative());
	String EIONI_Penning = ComUtil.isNullToZeroString(search_info.getEIONI_Penning());
	String EIONI_Reaction = ComUtil.isNullToZeroString(search_info.getEIONI_Reaction());
	String EIONI_Electronic = ComUtil.isNullToZeroString(search_info.getEIONI_Electronic());
	String EIONI_Vibrational = ComUtil.isNullToZeroString(search_info.getEIONI_Vibrational());
	String EIONI_Rotational = ComUtil.isNullToZeroString(search_info.getEIONI_Rotational());
	String EIONI_Radiative = ComUtil.isNullToZeroString(search_info.getEIONI_Radiative());
	String EIONI_Dielectronic = ComUtil.isNullToZeroString(search_info.getEIONI_Dielectronic());
	String EIONI_Three_Body = ComUtil.isNullToZeroString(search_info.getEIONI_Three_Body());
	String EIONI_Dissociative = ComUtil.isNullToZeroString(search_info.getEIONI_Dissociative());
	String EIONI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEIONI_Transfer_Ionization());
	String EIONI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEIONI_Thermal_Electron());
	String EIONI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEIONI_Electron_Loss());
	String EIONI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEIONI_Particle_Interchange());
	String EIONI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEIONI_Total_Neutral_Fragments());
	String EIONI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEIONI_Neutral_Fragments());
	String EIONI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEIONI_Total_Dissociative());
	String EIONI_Detachment = ComUtil.isNullToZeroString(search_info.getEIONI_Detachment());
	String EIONI_Autoionization = ComUtil.isNullToZeroString(search_info.getEIONI_Autoionization());
	String EIONI_Quenching = ComUtil.isNullToZeroString(search_info.getEIONI_Quenching());
	String EIONI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEIONI_X_ray_Production());
	String EIONI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEIONI_Relacxation_Reaction());
	String EIONI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEIONI_State_Selectivity());
	String EIONI_Photon = ComUtil.isNullToZeroString(search_info.getEIONI_Photon());
	String EIONI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEIONI_Ion_Pair_Production());
	String EIONI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEIONI_Charge_Transfer());
	String EIONI_de_Excitation = ComUtil.isNullToZeroString(search_info.getEIONI_de_Excitation());
	String EIONI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEIONI_Neutral_Product_Dissociation());
	String EIONI_Attachement = ComUtil.isNullToZeroString(search_info.getEIONI_Attachement());
	String EIONI_Inelastic = ComUtil.isNullToZeroString(search_info.getEIONI_Inelastic());
	String EIONI_Electron_Production = ComUtil.isNullToZeroString(search_info.getEIONI_Electron_Production());
	String EIONI_Vibrotational = ComUtil.isNullToZeroString(search_info.getEIONI_Vibrotational());
	
	//Electron Impact - Excitation
	String EEXCI_Total = ComUtil.isNullToZeroString(search_info.getEEXCI_Total());
	String EEXCI_Elastic = ComUtil.isNullToZeroString(search_info.getEEXCI_Elastic());
	String EEXCI_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEEXCI_Momentum_Transfer());
	String EEXCI_Single = ComUtil.isNullToZeroString(search_info.getEEXCI_Single());
	String EEXCI_Double = ComUtil.isNullToZeroString(search_info.getEEXCI_Double());
	String EEXCI_Multiple = ComUtil.isNullToZeroString(search_info.getEEXCI_Multiple());
	String EEXCI_Partial = ComUtil.isNullToZeroString(search_info.getEEXCI_Partial());
	String EEXCI_Associative = ComUtil.isNullToZeroString(search_info.getEEXCI_Associative());
	String EEXCI_Penning = ComUtil.isNullToZeroString(search_info.getEEXCI_Penning());
	String EEXCI_Reaction = ComUtil.isNullToZeroString(search_info.getEEXCI_Reaction());
	String EEXCI_Electronic = ComUtil.isNullToZeroString(search_info.getEEXCI_Electronic());
	String EEXCI_Vibrational = ComUtil.isNullToZeroString(search_info.getEEXCI_Vibrational());
	String EEXCI_Rotational = ComUtil.isNullToZeroString(search_info.getEEXCI_Rotational());
	String EEXCI_Radiative = ComUtil.isNullToZeroString(search_info.getEEXCI_Radiative());
	String EEXCI_Dielectronic = ComUtil.isNullToZeroString(search_info.getEEXCI_Dielectronic());
	String EEXCI_Three_Body = ComUtil.isNullToZeroString(search_info.getEEXCI_Three_Body());
	String EEXCI_Dissociative = ComUtil.isNullToZeroString(search_info.getEEXCI_Dissociative());
	String EEXCI_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEEXCI_Transfer_Ionization());
	String EEXCI_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEEXCI_Thermal_Electron());
	String EEXCI_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEEXCI_Electron_Loss());
	String EEXCI_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEEXCI_Particle_Interchange());
	String EEXCI_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEEXCI_Total_Neutral_Fragments());
	String EEXCI_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEEXCI_Neutral_Fragments());
	String EEXCI_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEEXCI_Total_Dissociative());
	String EEXCI_Detachment = ComUtil.isNullToZeroString(search_info.getEEXCI_Detachment());
	String EEXCI_Autoionization = ComUtil.isNullToZeroString(search_info.getEEXCI_Autoionization());
	String EEXCI_Quenching = ComUtil.isNullToZeroString(search_info.getEEXCI_Quenching());
	String EEXCI_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEEXCI_X_ray_Production());
	String EEXCI_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEEXCI_Relacxation_Reaction());
	String EEXCI_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEEXCI_State_Selectivity());
	String EEXCI_Photon = ComUtil.isNullToZeroString(search_info.getEEXCI_Photon());
	String EEXCI_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEEXCI_Ion_Pair_Production());
	String EEXCI_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEEXCI_Charge_Transfer());
	String EEXCI_de_Excitation = ComUtil.isNullToZeroString(search_info.getEEXCI_de_Excitation());
	String EEXCI_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEEXCI_Neutral_Product_Dissociation());
	String EEXCI_Attachement = ComUtil.isNullToZeroString(search_info.getEEXCI_Attachement());
	String EEXCI_Inelastic = ComUtil.isNullToZeroString(search_info.getEEXCI_Inelastic());
	String EEXCI_Electron_Production = ComUtil.isNullToZeroString(search_info.getEEXCI_Electron_Production());
	String EEXCI_Vibrotational = ComUtil.isNullToZeroString(search_info.getEEXCI_Vibrotational());
	
	//Electron Impact - Recombination
	String ERECO_Total = ComUtil.isNullToZeroString(search_info.getERECO_Total());
	String ERECO_Elastic = ComUtil.isNullToZeroString(search_info.getERECO_Elastic());
	String ERECO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getERECO_Momentum_Transfer());
	String ERECO_Single = ComUtil.isNullToZeroString(search_info.getERECO_Single());
	String ERECO_Double = ComUtil.isNullToZeroString(search_info.getERECO_Double());
	String ERECO_Multiple = ComUtil.isNullToZeroString(search_info.getERECO_Multiple());
	String ERECO_Partial = ComUtil.isNullToZeroString(search_info.getERECO_Partial());
	String ERECO_Associative = ComUtil.isNullToZeroString(search_info.getERECO_Associative());
	String ERECO_Penning = ComUtil.isNullToZeroString(search_info.getERECO_Penning());
	String ERECO_Reaction = ComUtil.isNullToZeroString(search_info.getERECO_Reaction());
	String ERECO_Electronic = ComUtil.isNullToZeroString(search_info.getERECO_Electronic());
	String ERECO_Vibrational = ComUtil.isNullToZeroString(search_info.getERECO_Vibrational());
	String ERECO_Rotational = ComUtil.isNullToZeroString(search_info.getERECO_Rotational());
	String ERECO_Radiative = ComUtil.isNullToZeroString(search_info.getERECO_Radiative());
	String ERECO_Dielectronic = ComUtil.isNullToZeroString(search_info.getERECO_Dielectronic());
	String ERECO_Three_Body = ComUtil.isNullToZeroString(search_info.getERECO_Three_Body());
	String ERECO_Dissociative = ComUtil.isNullToZeroString(search_info.getERECO_Dissociative());
	String ERECO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getERECO_Transfer_Ionization());
	String ERECO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getERECO_Thermal_Electron());
	String ERECO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getERECO_Electron_Loss());
	String ERECO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getERECO_Particle_Interchange());
	String ERECO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getERECO_Total_Neutral_Fragments());
	String ERECO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getERECO_Neutral_Fragments());
	String ERECO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getERECO_Total_Dissociative());
	String ERECO_Detachment = ComUtil.isNullToZeroString(search_info.getERECO_Detachment());
	String ERECO_Autoionization = ComUtil.isNullToZeroString(search_info.getERECO_Autoionization());
	String ERECO_Quenching = ComUtil.isNullToZeroString(search_info.getERECO_Quenching());
	String ERECO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getERECO_X_ray_Production());
	String ERECO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getERECO_Relacxation_Reaction());
	String ERECO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getERECO_State_Selectivity());
	String ERECO_Photon = ComUtil.isNullToZeroString(search_info.getERECO_Photon());
	String ERECO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getERECO_Ion_Pair_Production());
	String ERECO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getERECO_Charge_Transfer());
	String ERECO_de_Excitation = ComUtil.isNullToZeroString(search_info.getERECO_de_Excitation());
	String ERECO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getERECO_Neutral_Product_Dissociation());
	String ERECO_Attachement = ComUtil.isNullToZeroString(search_info.getERECO_Attachement());
	String ERECO_Inelastic = ComUtil.isNullToZeroString(search_info.getERECO_Inelastic());
	String ERECO_Electron_Production = ComUtil.isNullToZeroString(search_info.getERECO_Electron_Production());
	String ERECO_Vibrotational = ComUtil.isNullToZeroString(search_info.getERECO_Vibrotational());           
	
	
	//Electron Impact - Charge Transfer
	String ECHTR_Total = ComUtil.isNullToZeroString(search_info.getECHTR_Total());
	String ECHTR_Elastic = ComUtil.isNullToZeroString(search_info.getECHTR_Elastic());
	String ECHTR_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getECHTR_Momentum_Transfer());
	String ECHTR_Single = ComUtil.isNullToZeroString(search_info.getECHTR_Single());
	String ECHTR_Double = ComUtil.isNullToZeroString(search_info.getECHTR_Double());
	String ECHTR_Multiple = ComUtil.isNullToZeroString(search_info.getECHTR_Multiple());
	String ECHTR_Partial = ComUtil.isNullToZeroString(search_info.getECHTR_Partial());
	String ECHTR_Associative = ComUtil.isNullToZeroString(search_info.getECHTR_Associative());
	String ECHTR_Penning = ComUtil.isNullToZeroString(search_info.getECHTR_Penning());
	String ECHTR_Reaction = ComUtil.isNullToZeroString(search_info.getECHTR_Reaction());
	String ECHTR_Electronic = ComUtil.isNullToZeroString(search_info.getECHTR_Electronic());
	String ECHTR_Vibrational = ComUtil.isNullToZeroString(search_info.getECHTR_Vibrational());
	String ECHTR_Rotational = ComUtil.isNullToZeroString(search_info.getECHTR_Rotational());
	String ECHTR_Radiative = ComUtil.isNullToZeroString(search_info.getECHTR_Radiative());
	String ECHTR_Dielectronic = ComUtil.isNullToZeroString(search_info.getECHTR_Dielectronic());
	String ECHTR_Three_Body = ComUtil.isNullToZeroString(search_info.getECHTR_Three_Body());
	String ECHTR_Dissociative = ComUtil.isNullToZeroString(search_info.getECHTR_Dissociative());
	String ECHTR_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getECHTR_Transfer_Ionization());
	String ECHTR_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getECHTR_Thermal_Electron());
	String ECHTR_Electron_Loss = ComUtil.isNullToZeroString(search_info.getECHTR_Electron_Loss());
	String ECHTR_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getECHTR_Particle_Interchange());
	String ECHTR_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getECHTR_Total_Neutral_Fragments());
	String ECHTR_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getECHTR_Neutral_Fragments());
	String ECHTR_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getECHTR_Total_Dissociative());
	String ECHTR_Detachment = ComUtil.isNullToZeroString(search_info.getECHTR_Detachment());
	String ECHTR_Autoionization = ComUtil.isNullToZeroString(search_info.getECHTR_Autoionization());
	String ECHTR_Quenching = ComUtil.isNullToZeroString(search_info.getECHTR_Quenching());
	String ECHTR_X_ray_Production = ComUtil.isNullToZeroString(search_info.getECHTR_X_ray_Production());
	String ECHTR_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getECHTR_Relacxation_Reaction());
	String ECHTR_State_Selectivity = ComUtil.isNullToZeroString(search_info.getECHTR_State_Selectivity());
	String ECHTR_Photon = ComUtil.isNullToZeroString(search_info.getECHTR_Photon());
	String ECHTR_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getECHTR_Ion_Pair_Production());
	String ECHTR_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getECHTR_Charge_Transfer());
	String ECHTR_de_Excitation = ComUtil.isNullToZeroString(search_info.getECHTR_de_Excitation());
	String ECHTR_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getECHTR_Neutral_Product_Dissociation());
	String ECHTR_Attachement = ComUtil.isNullToZeroString(search_info.getECHTR_Attachement());
	String ECHTR_Inelastic = ComUtil.isNullToZeroString(search_info.getECHTR_Inelastic());
	String ECHTR_Electron_Production = ComUtil.isNullToZeroString(search_info.getECHTR_Electron_Production());
	String ECHTR_Vibrotational = ComUtil.isNullToZeroString(search_info.getECHTR_Vibrotational());
	
	//Electron Impact - Dissociation
	String EDISS_Total = ComUtil.isNullToZeroString(search_info.getEDISS_Total());
	String EDISS_Elastic = ComUtil.isNullToZeroString(search_info.getEDISS_Elastic());
	String EDISS_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEDISS_Momentum_Transfer());
	String EDISS_Single = ComUtil.isNullToZeroString(search_info.getEDISS_Single());
	String EDISS_Double = ComUtil.isNullToZeroString(search_info.getEDISS_Double());
	String EDISS_Multiple = ComUtil.isNullToZeroString(search_info.getEDISS_Multiple());
	String EDISS_Partial = ComUtil.isNullToZeroString(search_info.getEDISS_Partial());
	String EDISS_Associative = ComUtil.isNullToZeroString(search_info.getEDISS_Associative());
	String EDISS_Penning = ComUtil.isNullToZeroString(search_info.getEDISS_Penning());
	String EDISS_Reaction = ComUtil.isNullToZeroString(search_info.getEDISS_Reaction());
	String EDISS_Electronic = ComUtil.isNullToZeroString(search_info.getEDISS_Electronic());
	String EDISS_Vibrational = ComUtil.isNullToZeroString(search_info.getEDISS_Vibrational());
	String EDISS_Rotational = ComUtil.isNullToZeroString(search_info.getEDISS_Rotational());
	String EDISS_Radiative = ComUtil.isNullToZeroString(search_info.getEDISS_Radiative());
	String EDISS_Dielectronic = ComUtil.isNullToZeroString(search_info.getEDISS_Dielectronic());
	String EDISS_Three_Body = ComUtil.isNullToZeroString(search_info.getEDISS_Three_Body());
	String EDISS_Dissociative = ComUtil.isNullToZeroString(search_info.getEDISS_Dissociative());
	String EDISS_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEDISS_Transfer_Ionization());
	String EDISS_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEDISS_Thermal_Electron());
	String EDISS_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEDISS_Electron_Loss());
	String EDISS_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEDISS_Particle_Interchange());
	String EDISS_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEDISS_Total_Neutral_Fragments());
	String EDISS_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEDISS_Neutral_Fragments());
	String EDISS_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEDISS_Total_Dissociative());
	String EDISS_Detachment = ComUtil.isNullToZeroString(search_info.getEDISS_Detachment());
	String EDISS_Autoionization = ComUtil.isNullToZeroString(search_info.getEDISS_Autoionization());
	String EDISS_Quenching = ComUtil.isNullToZeroString(search_info.getEDISS_Quenching());
	String EDISS_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEDISS_X_ray_Production());
	String EDISS_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEDISS_Relacxation_Reaction());
	String EDISS_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEDISS_State_Selectivity());
	String EDISS_Photon = ComUtil.isNullToZeroString(search_info.getEDISS_Photon());
	String EDISS_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEDISS_Ion_Pair_Production());
	String EDISS_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEDISS_Charge_Transfer());
	String EDISS_de_Excitation = ComUtil.isNullToZeroString(search_info.getEDISS_de_Excitation());
	String EDISS_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEDISS_Neutral_Product_Dissociation());
	String EDISS_Attachement = ComUtil.isNullToZeroString(search_info.getEDISS_Attachement());
	String EDISS_Inelastic = ComUtil.isNullToZeroString(search_info.getEDISS_Inelastic());
	String EDISS_Electron_Production = ComUtil.isNullToZeroString(search_info.getEDISS_Electron_Production());
	String EDISS_Vibrotational = ComUtil.isNullToZeroString(search_info.getEDISS_Vibrotational());    
	
	//Electron Impact - Attachment
	String EATTA_Total = ComUtil.isNullToZeroString(search_info.getEATTA_Total());
	String EATTA_Elastic = ComUtil.isNullToZeroString(search_info.getEATTA_Elastic());
	String EATTA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEATTA_Momentum_Transfer());
	String EATTA_Single = ComUtil.isNullToZeroString(search_info.getEATTA_Single());
	String EATTA_Double = ComUtil.isNullToZeroString(search_info.getEATTA_Double());
	String EATTA_Multiple = ComUtil.isNullToZeroString(search_info.getEATTA_Multiple());
	String EATTA_Partial = ComUtil.isNullToZeroString(search_info.getEATTA_Partial());
	String EATTA_Associative = ComUtil.isNullToZeroString(search_info.getEATTA_Associative());
	String EATTA_Penning = ComUtil.isNullToZeroString(search_info.getEATTA_Penning());
	String EATTA_Reaction = ComUtil.isNullToZeroString(search_info.getEATTA_Reaction());
	String EATTA_Electronic = ComUtil.isNullToZeroString(search_info.getEATTA_Electronic());
	String EATTA_Vibrational = ComUtil.isNullToZeroString(search_info.getEATTA_Vibrational());
	String EATTA_Rotational = ComUtil.isNullToZeroString(search_info.getEATTA_Rotational());
	String EATTA_Radiative = ComUtil.isNullToZeroString(search_info.getEATTA_Radiative());
	String EATTA_Dielectronic = ComUtil.isNullToZeroString(search_info.getEATTA_Dielectronic());
	String EATTA_Three_Body = ComUtil.isNullToZeroString(search_info.getEATTA_Three_Body());
	String EATTA_Dissociative = ComUtil.isNullToZeroString(search_info.getEATTA_Dissociative());
	String EATTA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEATTA_Transfer_Ionization());
	String EATTA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEATTA_Thermal_Electron());
	String EATTA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEATTA_Electron_Loss());
	String EATTA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEATTA_Particle_Interchange());
	String EATTA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEATTA_Total_Neutral_Fragments());
	String EATTA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEATTA_Neutral_Fragments());
	String EATTA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEATTA_Total_Dissociative());
	String EATTA_Detachment = ComUtil.isNullToZeroString(search_info.getEATTA_Detachment());
	String EATTA_Autoionization = ComUtil.isNullToZeroString(search_info.getEATTA_Autoionization());
	String EATTA_Quenching = ComUtil.isNullToZeroString(search_info.getEATTA_Quenching());
	String EATTA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEATTA_X_ray_Production());
	String EATTA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEATTA_Relacxation_Reaction());
	String EATTA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEATTA_State_Selectivity());
	String EATTA_Photon = ComUtil.isNullToZeroString(search_info.getEATTA_Photon());
	String EATTA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEATTA_Ion_Pair_Production());
	String EATTA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEATTA_Charge_Transfer());
	String EATTA_de_Excitation = ComUtil.isNullToZeroString(search_info.getEATTA_de_Excitation());
	String EATTA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEATTA_Neutral_Product_Dissociation());
	String EATTA_Attachement = ComUtil.isNullToZeroString(search_info.getEATTA_Attachement());
	String EATTA_Inelastic = ComUtil.isNullToZeroString(search_info.getEATTA_Inelastic());
	String EATTA_Electron_Production = ComUtil.isNullToZeroString(search_info.getEATTA_Electron_Production());
	String EATTA_Vibrotational = ComUtil.isNullToZeroString(search_info.getEATTA_Vibrotational());     
	
	//Electron Impact - Reaction
	String EREAC_Total = ComUtil.isNullToZeroString(search_info.getEREAC_Total());
	String EREAC_Elastic = ComUtil.isNullToZeroString(search_info.getEREAC_Elastic());
	String EREAC_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEREAC_Momentum_Transfer());
	String EREAC_Single = ComUtil.isNullToZeroString(search_info.getEREAC_Single());
	String EREAC_Double = ComUtil.isNullToZeroString(search_info.getEREAC_Double());
	String EREAC_Multiple = ComUtil.isNullToZeroString(search_info.getEREAC_Multiple());
	String EREAC_Partial = ComUtil.isNullToZeroString(search_info.getEREAC_Partial());
	String EREAC_Associative = ComUtil.isNullToZeroString(search_info.getEREAC_Associative());
	String EREAC_Penning = ComUtil.isNullToZeroString(search_info.getEREAC_Penning());
	String EREAC_Reaction = ComUtil.isNullToZeroString(search_info.getEREAC_Reaction());
	String EREAC_Electronic = ComUtil.isNullToZeroString(search_info.getEREAC_Electronic());
	String EREAC_Vibrational = ComUtil.isNullToZeroString(search_info.getEREAC_Vibrational());
	String EREAC_Rotational = ComUtil.isNullToZeroString(search_info.getEREAC_Rotational());
	String EREAC_Radiative = ComUtil.isNullToZeroString(search_info.getEREAC_Radiative());
	String EREAC_Dielectronic = ComUtil.isNullToZeroString(search_info.getEREAC_Dielectronic());
	String EREAC_Three_Body = ComUtil.isNullToZeroString(search_info.getEREAC_Three_Body());
	String EREAC_Dissociative = ComUtil.isNullToZeroString(search_info.getEREAC_Dissociative());
	String EREAC_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEREAC_Transfer_Ionization());
	String EREAC_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEREAC_Thermal_Electron());
	String EREAC_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEREAC_Electron_Loss());
	String EREAC_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEREAC_Particle_Interchange());
	String EREAC_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEREAC_Total_Neutral_Fragments());
	String EREAC_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEREAC_Neutral_Fragments());
	String EREAC_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEREAC_Total_Dissociative());
	String EREAC_Detachment = ComUtil.isNullToZeroString(search_info.getEREAC_Detachment());
	String EREAC_Autoionization = ComUtil.isNullToZeroString(search_info.getEREAC_Autoionization());
	String EREAC_Quenching = ComUtil.isNullToZeroString(search_info.getEREAC_Quenching());
	String EREAC_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEREAC_X_ray_Production());
	String EREAC_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEREAC_Relacxation_Reaction());
	String EREAC_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEREAC_State_Selectivity());
	String EREAC_Photon = ComUtil.isNullToZeroString(search_info.getEREAC_Photon());
	String EREAC_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEREAC_Ion_Pair_Production());
	String EREAC_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEREAC_Charge_Transfer());
	String EREAC_de_Excitation = ComUtil.isNullToZeroString(search_info.getEREAC_de_Excitation());
	String EREAC_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEREAC_Neutral_Product_Dissociation());
	String EREAC_Attachement = ComUtil.isNullToZeroString(search_info.getEREAC_Attachement());
	String EREAC_Inelastic = ComUtil.isNullToZeroString(search_info.getEREAC_Inelastic());
	String EREAC_Electron_Production = ComUtil.isNullToZeroString(search_info.getEREAC_Electron_Production());
	String EREAC_Vibrotational = ComUtil.isNullToZeroString(search_info.getEREAC_Vibrotational());  
	
	//Electron Impact - Detachment     
	String EDETA_Total = ComUtil.isNullToZeroString(search_info.getEDETA_Total());
	String EDETA_Elastic = ComUtil.isNullToZeroString(search_info.getEDETA_Elastic());
	String EDETA_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEDETA_Momentum_Transfer());
	String EDETA_Single = ComUtil.isNullToZeroString(search_info.getEDETA_Single());
	String EDETA_Double = ComUtil.isNullToZeroString(search_info.getEDETA_Double());
	String EDETA_Multiple = ComUtil.isNullToZeroString(search_info.getEDETA_Multiple());
	String EDETA_Partial = ComUtil.isNullToZeroString(search_info.getEDETA_Partial());
	String EDETA_Associative = ComUtil.isNullToZeroString(search_info.getEDETA_Associative());
	String EDETA_Penning = ComUtil.isNullToZeroString(search_info.getEDETA_Penning());
	String EDETA_Reaction = ComUtil.isNullToZeroString(search_info.getEDETA_Reaction());
	String EDETA_Electronic = ComUtil.isNullToZeroString(search_info.getEDETA_Electronic());
	String EDETA_Vibrational = ComUtil.isNullToZeroString(search_info.getEDETA_Vibrational());
	String EDETA_Rotational = ComUtil.isNullToZeroString(search_info.getEDETA_Rotational());
	String EDETA_Radiative = ComUtil.isNullToZeroString(search_info.getEDETA_Radiative());
	String EDETA_Dielectronic = ComUtil.isNullToZeroString(search_info.getEDETA_Dielectronic());
	String EDETA_Three_Body = ComUtil.isNullToZeroString(search_info.getEDETA_Three_Body());
	String EDETA_Dissociative = ComUtil.isNullToZeroString(search_info.getEDETA_Dissociative());
	String EDETA_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEDETA_Transfer_Ionization());
	String EDETA_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEDETA_Thermal_Electron());
	String EDETA_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEDETA_Electron_Loss());
	String EDETA_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEDETA_Particle_Interchange());
	String EDETA_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEDETA_Total_Neutral_Fragments());
	String EDETA_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEDETA_Neutral_Fragments());
	String EDETA_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEDETA_Total_Dissociative());
	String EDETA_Detachment = ComUtil.isNullToZeroString(search_info.getEDETA_Detachment());
	String EDETA_Autoionization = ComUtil.isNullToZeroString(search_info.getEDETA_Autoionization());
	String EDETA_Quenching = ComUtil.isNullToZeroString(search_info.getEDETA_Quenching());
	String EDETA_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEDETA_X_ray_Production());
	String EDETA_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEDETA_Relacxation_Reaction());
	String EDETA_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEDETA_State_Selectivity());
	String EDETA_Photon = ComUtil.isNullToZeroString(search_info.getEDETA_Photon());
	String EDETA_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEDETA_Ion_Pair_Production());
	String EDETA_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEDETA_Charge_Transfer());
	String EDETA_de_Excitation = ComUtil.isNullToZeroString(search_info.getEDETA_de_Excitation());
	String EDETA_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEDETA_Neutral_Product_Dissociation());
	String EDETA_Attachement = ComUtil.isNullToZeroString(search_info.getEDETA_Attachement());
	String EDETA_Inelastic = ComUtil.isNullToZeroString(search_info.getEDETA_Inelastic());
	String EDETA_Electron_Production = ComUtil.isNullToZeroString(search_info.getEDETA_Electron_Production());
	String EDETA_Vibrotational = ComUtil.isNullToZeroString(search_info.getEDETA_Vibrotational()); 
	
	//Electron Impact - Absorption 
	String EABSO_Total = ComUtil.isNullToZeroString(search_info.getEABSO_Total());
	String EABSO_Elastic = ComUtil.isNullToZeroString(search_info.getEABSO_Elastic());
	String EABSO_Momentum_Transfer = ComUtil.isNullToZeroString(search_info.getEABSO_Momentum_Transfer());
	String EABSO_Single = ComUtil.isNullToZeroString(search_info.getEABSO_Single());
	String EABSO_Double = ComUtil.isNullToZeroString(search_info.getEABSO_Double());
	String EABSO_Multiple = ComUtil.isNullToZeroString(search_info.getEABSO_Multiple());
	String EABSO_Partial = ComUtil.isNullToZeroString(search_info.getEABSO_Partial());
	String EABSO_Associative = ComUtil.isNullToZeroString(search_info.getEABSO_Associative());
	String EABSO_Penning = ComUtil.isNullToZeroString(search_info.getEABSO_Penning());
	String EABSO_Reaction = ComUtil.isNullToZeroString(search_info.getEABSO_Reaction());
	String EABSO_Electronic = ComUtil.isNullToZeroString(search_info.getEABSO_Electronic());
	String EABSO_Vibrational = ComUtil.isNullToZeroString(search_info.getEABSO_Vibrational());
	String EABSO_Rotational = ComUtil.isNullToZeroString(search_info.getEABSO_Rotational());
	String EABSO_Radiative = ComUtil.isNullToZeroString(search_info.getEABSO_Radiative());
	String EABSO_Dielectronic = ComUtil.isNullToZeroString(search_info.getEABSO_Dielectronic());
	String EABSO_Three_Body = ComUtil.isNullToZeroString(search_info.getEABSO_Three_Body());
	String EABSO_Dissociative = ComUtil.isNullToZeroString(search_info.getEABSO_Dissociative());
	String EABSO_Transfer_Ionization = ComUtil.isNullToZeroString(search_info.getEABSO_Transfer_Ionization());
	String EABSO_Thermal_Electron = ComUtil.isNullToZeroString(search_info.getEABSO_Thermal_Electron());
	String EABSO_Electron_Loss = ComUtil.isNullToZeroString(search_info.getEABSO_Electron_Loss());
	String EABSO_Particle_Interchange = ComUtil.isNullToZeroString(search_info.getEABSO_Particle_Interchange());
	String EABSO_Total_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEABSO_Total_Neutral_Fragments());
	String EABSO_Neutral_Fragments = ComUtil.isNullToZeroString(search_info.getEABSO_Neutral_Fragments());
	String EABSO_Total_Dissociative = ComUtil.isNullToZeroString(search_info.getEABSO_Total_Dissociative());
	String EABSO_Detachment = ComUtil.isNullToZeroString(search_info.getEABSO_Detachment());
	String EABSO_Autoionization = ComUtil.isNullToZeroString(search_info.getEABSO_Autoionization());
	String EABSO_Quenching = ComUtil.isNullToZeroString(search_info.getEABSO_Quenching());
	String EABSO_X_ray_Production = ComUtil.isNullToZeroString(search_info.getEABSO_X_ray_Production());
	String EABSO_Relacxation_Reaction = ComUtil.isNullToZeroString(search_info.getEABSO_Relacxation_Reaction());
	String EABSO_State_Selectivity = ComUtil.isNullToZeroString(search_info.getEABSO_State_Selectivity());
	String EABSO_Photon = ComUtil.isNullToZeroString(search_info.getEABSO_Photon());
	String EABSO_Ion_Pair_Production = ComUtil.isNullToZeroString(search_info.getEABSO_Ion_Pair_Production());
	String EABSO_Charge_Transfer = ComUtil.isNullToZeroString(search_info.getEABSO_Charge_Transfer());
	String EABSO_de_Excitation = ComUtil.isNullToZeroString(search_info.getEABSO_de_Excitation());
	String EABSO_Neutral_Product_Dissociation = ComUtil.isNullToZeroString(search_info.getEABSO_Neutral_Product_Dissociation());
	String EABSO_Attachement = ComUtil.isNullToZeroString(search_info.getEABSO_Attachement());
	String EABSO_Inelastic = ComUtil.isNullToZeroString(search_info.getEABSO_Inelastic());
	String EABSO_Electron_Production = ComUtil.isNullToZeroString(search_info.getEABSO_Electron_Production());
	String EABSO_Vibrotational = ComUtil.isNullToZeroString(search_info.getEABSO_Vibrotational()); 

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
	
	String EI_COUNT = ComUtil.isNullToZeroString(search_info.getE_COUNT());
	String PI_COUNT = ComUtil.isNullToZeroString(search_info.getP_COUNT());
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
    for(i = 1; i <= 3 ; i++ ){   
        document.getElementById('tab'+i).className = "";   
        document.getElementById('content'+i).className = "content hide";   
    }   
    document.getElementById('tab'+idx).className = "active";   
    document.getElementById('content'+idx).className = "content show";   
}  
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
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
<table class="tab5">
<col width="15%">
<col width="40%">
<col width="15%">
<col width="50%">
  <tr>
    <th class="thc" colspan="4">검색 조건</th>
  </tr>
  <tr>
  	<td class="tdr">대분류 : </td>
    <td class="tdl"><%=st_param_TB%></td>
    <td class="tdr">데이터분류 : </td>
    <td class="tdl"><%=st_param_DB%></td>    
  </tr>
  <tr>
    <td class="tdr">표적입자 : </td>
    <td class="tdl"><%=param_part1_sym%></td>
    <td class="tdr">입사입자 : </td>
    <td class="tdl"><%=param_part2_sym%></td>
  </tr>
    <tr>
    <td class="tdr">주프로세스 : </td>
    <td class="tdl"><%=st_param_MP%></td>
    <td class="tdr"></td>
    <td class="tdl"></td>
  </tr>
</table>
   
<div id="navcontainer">
    <ul class="navlist">
       <!-- CSS Tabs -->
		<li><a id='tab1' title="Electron Impact" href="javascript:tabs('1');" class='active'>Electron Impact (<%=EI_COUNT %>)</a></li>
		<li><a id='tab2' title="Photon Impact" href="javascript:tabs('2');">Photon Impact (<%=PI_COUNT %>)</a></li>
		<li><a id='tab3' title="Heavy particle Impact" href="javascript:tabs('3');">Heavy particle Impact (<%=HI_COUNT %>)</a></li>		
	</ul>
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
		<tr>
    		<th class="thc" colspan="6">Scattering</th>
 		</tr>
 		<%for(int i = 0; i < 10 ; i++){ %>
 		<tr>
	 		<td class="tdr">대분류 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
		    <td class="tdr">데이터분류 : </td>
		    <td class="tdl"><%=st_param_DB%></td>    
		    <td class="tdr">대분류 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
 		</tr>
		<% }%>
	</table>
</div> 
<div id='content2' class='content hide'>  
    <table class="tab2" width="650" height="650">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<tr>
    		<th class="thc" colspan="6">Scattering</th>
 		</tr>
 		<%for(int i = 0; i < 15 ; i++){ %>
 		<tr>
	 		<td class="tdr">대분류2 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
		    <td class="tdr">데이터분류2 : </td>
		    <td class="tdl"><%=st_param_DB%></td>    
		    <td class="tdr">대분류2 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
 		</tr>
		<% }%>
	</table>
</div> 
<div id='content3' class='content hide'>  
    <table class="tab2" width="650" height="650">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<col width="17%">
		<col width="8%">
		<tr>
    		<th class="thc" colspan="6">Scattering</th>
 		</tr>
 		<%for(int i = 0; i < 20 ; i++){ %>
 		<tr>
	 		<td class="tdr">대분류3 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
		    <td class="tdr">데이터분류3 : </td>
		    <td class="tdl"><%=st_param_DB%></td>    
		    <td class="tdr">대분류3 : </td>
		    <td class="tdl"><%=st_param_TB%></td>
 		</tr>
		<% }%>
	</table>
</div> 

</form>
</body>
</html>