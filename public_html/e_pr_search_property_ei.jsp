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
		search_exp = "Target particle : "+param_part2_sym + ", Projectile particle : " + param_part1_sym;
	}else{
		search_exp = "Projectile particle : " + param_part1_sym;
	}
	search_exp = search_exp + ", Categorize : " +codeControl.getExpName(param_TB);
	search_exp = search_exp + ", Collision data categorize : " +codeControl.getExpName(param_DB);
	
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

		
	String EI_COUNT = ComUtil.isNullToZeroString(search_info.getE_COUNT());
	
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
        <li><a id='tab1' title="Electron Impact" href="javascript:tabs('1');" class='active'>Electron Impact (<%=EI_COUNT %> 건)</a></li>  
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
				if(!ESCAT_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ESCAT_Total'); return false;"><%= ESCAT_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ESCAT_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!EIONI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Total'); return false;"><%= EIONI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!EEXCI_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Total'); return false;"><%= EEXCI_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!ERECO_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ERECO_Total'); return false;"><%= ERECO_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ERECO_Total%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Elastic :</td>
			<%
				if(!ESCAT_Elastic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ESCAT_Elastic'); return false;"><%= ESCAT_Elastic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ESCAT_Elastic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Single :</td>
			<%
				if(!EIONI_Single.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Single'); return false;"><%= EIONI_Single%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Single%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electronic :</td>
			<%
				if(!EEXCI_Electronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Electronic'); return false;"><%= EEXCI_Electronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Electronic%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!ERECO_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ERECO_Radiative'); return false;"><%= ERECO_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ERECO_Radiative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold">Momentum transfer :</td>
			<%
				if(!ESCAT_Momentum_Transfer.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ESCAT_Momentum_Transfer'); return false;"><%= ESCAT_Momentum_Transfer%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ESCAT_Momentum_Transfer%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Double :</td>
			<%
				if(!EIONI_Double.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Double'); return false;"><%= EIONI_Double%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Double%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Vibrational :</td>
			<%
				if(!EEXCI_Vibrational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Vibrational'); return false;"><%= EEXCI_Vibrational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Vibrational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dielectronic :</td>
			<%
				if(!ERECO_Dielectronic.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ERECO_Dielectronic'); return false;"><%= ERECO_Dielectronic%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ERECO_Dielectronic%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Multiple :</td>
			<%
				if(!EIONI_Multiple.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Multiple'); return false;"><%= EIONI_Multiple%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Multiple%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Rotational :</td>
			<%
				if(!EEXCI_Rotational.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Rotational'); return false;"><%= EEXCI_Rotational%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Rotational%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Three body :</td>
			<%
				if(!ERECO_Three_Body.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ERECO_Three_Body'); return false;"><%= ERECO_Three_Body%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ERECO_Three_Body%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Partial :</td>
			<%
				if(!EIONI_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Partial'); return false;"><%= EIONI_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!EEXCI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Associative'); return false;"><%= EEXCI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!ERECO_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('ERECO_Dissociative'); return false;"><%= ERECO_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= ERECO_Dissociative%> 건</td>
			<%
				}
			%>
		</tr>
		<tr height="38">
			<td class="tdrbold"></td>
			<td class="tdl"></td>
			<td class="tdrbold">Associative :</td>
			<%
				if(!EIONI_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Associative'); return false;"><%= EIONI_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Associative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!EEXCI_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Radiative'); return false;"><%= EEXCI_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Radiative%> 건</td>
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
				if(!EIONI_Penning.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EIONI_Penning'); return false;"><%= EIONI_Penning%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EIONI_Penning%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!EEXCI_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EEXCI_Dissociative'); return false;"><%= EEXCI_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EEXCI_Dissociative%> 건</td>
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
				if(!EDISS_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EDISS_Total'); return false;"><%= EDISS_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EDISS_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Total :</td>
			<%
				if(!EATTA_Total.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Total'); return false;"><%= EATTA_Total%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Total%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Electron loss :</td>
			<%
				if(!EREAC_Electron_Loss.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EREAC_Electron_Loss'); return false;"><%= EREAC_Electron_Loss%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EREAC_Electron_Loss%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">
			<td class="tdrbold">Partial :</td>
			<%
				if(!EDISS_Partial.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EDISS_Partial'); return false;"><%= EDISS_Partial%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EDISS_Partial%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Dissociative :</td>
			<%
				if(!EATTA_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Dissociative'); return false;"><%= EATTA_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Dissociative%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Particle Interchange :</td>
			<%
				if(!EREAC_Particle_Interchange.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EREAC_Particle_Interchange'); return false;"><%= EREAC_Particle_Interchange%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EREAC_Particle_Interchange%> 건</td>
			<%
				}
			%>
			<td class="tdrbold"></td>
			<td class="tdl"></td>
		</tr>
		<tr height="38">			
			<td class="tdrbold">Total Neutral Fragments :</td>
			<%
				if(!EDISS_Total_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EDISS_Total_Neutral_Fragments'); return false;"><%= EDISS_Total_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EDISS_Total_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Associative :</td>
			<%
				if(!EATTA_Associative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Associative'); return false;"><%= EATTA_Associative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Associative%> 건</td>
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
				if(!EDISS_Neutral_Fragments.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EDISS_Neutral_Fragments'); return false;"><%= EDISS_Neutral_Fragments%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EDISS_Neutral_Fragments%> 건</td>
			<%
				}
			%>
			<td class="tdrbold">Radiative :</td>
			<%
				if(!EATTA_Radiative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Radiative'); return false;"><%= EATTA_Radiative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Radiative%> 건</td>
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
				if(!EATTA_Thermal_Electron.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Thermal_Electron'); return false;"><%= EATTA_Thermal_Electron%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Thermal_Electron%> 건</td>
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
				if(!EATTA_Total_Dissociative.equalsIgnoreCase("0")){
			%>
				<td class="tdl"><a href="javascript:void(0);"  onClick="searchview('EATTA_Total_Dissociative'); return false;"><%= EATTA_Total_Dissociative%></a> 건</td>
			<%
				}else{
			%>
				<td class="tdl"><%= EATTA_Total_Dissociative%> 건</td>
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