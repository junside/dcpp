<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>     
<%@ page import="java.text.*" %>
<%@ page import="nfri.dcpp.com.lang.*" %>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="articleControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<jsp:useBean id="part_info" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>
<jsp:useBean id="xsams_info" class="nfri.dcpp.properties.business.Ctr_XSAMS_Process" scope="page"/>

<%@ page import=" java.io.*"  %>
<%@ page import=" javax.xml.parsers.* "  %>
<%@ page import=" javax.xml.transform.* "  %>
<%@ page import=" javax.xml.transform.stream.*"  %>
<%@ page import=" javax.xml.transform.dom.*"%>

<%@ page import=" org.jdom2.* "%>
<%@ page import=" org.jdom2.Attribute "%>
<%@ page import=" org.jdom2.Document "%>
<%@ page import=" org.jdom2.Element "%>
<%@ page import=" org.jdom2.input.SAXBuilder "%>
<%@ page import=" org.jdom2.JDOMException "%>
<%@ page import=" org.jdom2.output.XMLOutputter "%>
<%@ page import=" org.jdom2.output.Format "%>
<%@ page import=" org.jdom2.Namespace "%>

<%
		//String pr_no = "B201100365";
		//String pr_no = "B201212805";
		String pr_no = request.getParameter("pr_no");
		//String artcl_no = request.getParameter("artcl_no");
		
		//파일 다운로드를 위한 변수
		String file_path = "";
		String file_name = "";
		String file_ext = "";
		
		/*
		* <Species> 부분 데이터
		* 1. 기본 정보 가져오기
		*
		*/
		Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
		//대분류
		String tb_value = spec_info.getPL_BI_TOP_BRANCH();
		//데이터분류
		String db_value = spec_info.getPL_BI_DATA_BRANCH();
		//주프로세스
		String mp_value = spec_info.getPL_BI_MAIN_PROC();
		//부프로세스
		String sp_value = spec_info.getPL_BI_SUB_PROC();
		//충돌방식
		String imp_value = spec_info.getPL_BI_IMP_CLASS();
		//검증구분
		String exp_value = spec_info.getPL_BI_EXP_THE_REC();
		//표현식 정보 가져오기
		String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
		//참고문헌 번호
		String artcl_no = spec_info.getPL_RAI_ARTCL_NUM();
		
		//Basic_Part_Info part_data = part_info.selectPartInfo(part_no);
		
		
		File file= null;
		boolean skip= false;
		String client= "";
		
		//Document doc = null;
		Document doc = new Document();
		
		SAXBuilder builder = new SAXBuilder();
		
		//파일 읽어 오기
		//try{
		//	doc = builder.build(new File("c:\\template.xsams"));	
		//}catch (Exception e) {
		//	e.printStackTrace();
		//}
    	
		//Root 알아내기
    	//Element e_Root = doc.getRootElement();
    	//String str_Root = e_Root.getText();
    	//System.out.println("Root : " + str_Root);
    	
    	/*
		* Namespace 지정하기
		*/
    	Namespace n_xmlns = Namespace.getNamespace("http://vamdc.org/xml/xsams/1.0");
    	Namespace n_xmlns_cml = Namespace.getNamespace("cml", "http://www.xml-cml.org/schema");
    	Namespace n_xmlns_xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

    	/*
		* <XSAMSData> 부분 데이터
		* 1. Root 엘리먼트 생성
		*/ 
		//Element xsams_root = new Element("XSAMSData");
    	Element xsams_root = new Element("XSAMSData", n_xmlns);
    	xsams_root.addNamespaceDeclaration(n_xmlns);
    	xsams_root.addNamespaceDeclaration(n_xmlns_xsi);
    	xsams_root.addNamespaceDeclaration(n_xmlns_cml);
    	xsams_root.setAttribute("schemaLocation", "http://vamdc.org/xml/xsams/1.0 http://vamdc.org/xml/xsams/1.0/", n_xmlns_xsi);
    	

    	/*
		* 루트 엘리먼트에 추가할 요소(Element) 객체를 생성
		* 2. Sources 정보 시작
		*/

    	Element sources = new Element("Sources");
    	
    	/*
		* <Sources> 부분 데이터
		* 2.1. 논문 정보 가져오기
		*/
		Article_Info art_Info = (Article_Info)articleControl.searchInfo(artcl_no);
		
		String j_code = art_Info.getPL_RACI_CODE_ID(); //1. Category
		String j_title = art_Info.getPL_RAI_JOUR_TITLE(); //2. SourceName
		String j_year = art_Info.getPL_RAI_JOUR_YEAR(); //3. Year
		String a_auth_e = art_Info.getPL_RAI_ARTCL_AUTH_ENAME(); //4. Authors
		//저자 리스트 분리 처리
		String[] auth = ComUtil.splitString(a_auth_e, ",");
		//System.out.println("a_auth_e : " + a_auth_e);
		
		String a_title = art_Info.getPL_RAI_ARTCL_TITLE(); //5. Title
		String j_vol = art_Info.getPL_RAI_JOUR_VOL(); //6. Volumn
		String a_stp = art_Info.getPL_RAI_ARTCL_ST_PAGE(); //7. PageBegin
		String a_edp = art_Info.getPL_RAI_ARTCL_ED_PAGE(); //8. PageEnd

		//String j_num = art_Info.getPL_RAI_JOUR_NUM();
		String a_doi = art_Info.getPL_RAI_ARTCL_DOI();
		
    	//2.1 Source
    	Element source = new Element("Source");
    	//PL_RAI_ARTCL_NUM
    	source.setAttribute("sourceID", "B-DCPP-"+artcl_no);
    	
    	//2.1.1 Category : Journal Category
    	Element j_category = new Element("Category");    	
    	j_category.setText(j_code);
    	source.setContent(j_category); 
    	
    	//2.1.2 Source Name : Journal Name
    	Element j_source_name = new Element("SourceName");    	   	
    	j_source_name.setText(j_title);    	
    	source.addContent(j_source_name);
    	
    	//2.1.3 Year : Journal Year
    	Element journal_year = new Element("Year");    	   	
    	journal_year.setText(j_year);    	
    	source.addContent(journal_year);
    	
    	//2.1.4 Authors : Journal Authors
    	Element journal_authors = new Element("Authors");
    	
    	//System.out.println("auth.length : " + auth.length);
    	
    	for(int i = 0; i < auth.length; i++){
    		Element j_author = new Element("Author");
    		Element j_author_name = new Element("Name");
    	
    		String auth_value = auth[i].trim();
    		
    		if(auth_value.substring(0,3).compareToIgnoreCase("and") == 0){
    			auth_value = auth_value.substring(4);
    		}
    		
    		//System.out.println("auth_value : " + auth_value);
    		
    		j_author_name.setText(auth_value);
    		j_author.addContent(j_author_name);    		
    		journal_authors.addContent(j_author);     		   		
    	}    	    	
    	    	
    	source.setContent(journal_authors);
    	
    	//2.1.5 Title : Article Title
    	Element article_title = new Element("Title");    	   	
    	article_title.setText(a_title);    	
    	source.addContent(article_title);
    	
    	//2.1.6 Volume : Journal Volume
    	Element journal_vol = new Element("Volume");    	   	
    	journal_vol.setText(j_vol);    	
    	source.addContent(journal_vol);
    	
    	//2.1.7 DigitalObjectIdentifier : Article DOI
    	Element article_doi = new Element("DigitalObjectIdentifier");    	   	
    	article_doi.setText(a_doi);    	
    	source.addContent(article_doi);
    	
    	//2.1.8 PageBegin : Article Page Begin
    	Element article_page_begin = new Element("PageBegin");    	   	
    	article_page_begin.setText(a_stp);    	
    	source.addContent(article_page_begin);
    	
    	//2.1.9 PageEnd : Article Page End
    	Element article_page_end = new Element("PageEnd");    	   	
    	article_page_end.setText(a_edp);    	
    	source.addContent(article_page_end);
    	
    	//2.1.10 최종 Source 추가
    	sources.setContent(source);
    	xsams_root.addContent(sources);
    	
    	//3. Methods
    	Element methods = new Element("Methods");
    	
    	//3.1 Method : MDER Method
    	Element mder_method = new Element("Method");    	   	
    	mder_method.setAttribute("methodID", "MDER");
    	
    	//3.1.1 Category : MDER Method Category
    	Element mder_category = new Element("Category");    	   	
    	mder_category.setText("derived");
    	mder_method.addContent(mder_category);
    	
    	//3.1.2 Description : MDER Method Description
    	Element mder_description = new Element("Description");
    	mder_description.setText("derived");
    	mder_method.addContent(mder_description);
    	
    	//3.2 Method : MTHE Method
    	Element mthe_method = new Element("Method");    	   	
    	mthe_method.setAttribute("methodID", "MTHE");
    	
    	//3.2.1 Category : MTHE Method Category
    	Element mthe_category = new Element("Category");    	   	
    	mthe_category.setText("theory");
    	mthe_method.addContent(mthe_category);
    	
    	//3.2.2 Description : MTHE Method Description
    	Element mthe_description = new Element("Description");
    	mthe_description.setText("theory");
    	mthe_method.addContent(mthe_description);
    	
    	//3.3 Method : MEXP Method
    	Element mexp_method = new Element("Method");    	   	
    	mexp_method.setAttribute("methodID", "MEXP");
    	
    	//3.3.1 Category : MEXP Method Category
    	Element mexp_category = new Element("Category");    	   	
    	mexp_category.setText("experiment");
    	mexp_method.addContent(mexp_category);
    	
    	//3.3.2 Description : MEXP Method Description
    	Element mexp_description = new Element("Description");
    	mexp_description.setText("experiment");
    	mexp_method.addContent(mexp_description);
    	
    	methods.addContent(mder_method);    	
    	methods.addContent(mthe_method);
    	methods.addContent(mexp_method); 	
    	
    	/*
		* 루트 엘리먼트에 추가할 요소(Element) 객체를 생성
		* 4. Species 정보 시작
		*/
		
    	Element species = new Element("Species");
    	    		
		Vector equa_info = property_info.selectEquationInfo(pr_no);
		
    	//Properties_Basic_Info info = property_info.selectViewPropertySpecInfo(pro_no);
			
		//System.out.println(equa_info.size());		
				
		Element atoms = new Element("Atoms");
		
		Element molcules = new Element("Molecules");
		
		
		//int species_check = 0;
		int atoms_check = 0;
		int molcules_check = 0;
		
		Vector<String> s_reactant = new Vector<String>();
		Vector<String> s_product = new Vector<String>();
		
		for(int i = 0; i<equa_info.size(); i++){
			Properties_Equation_Get_DbInfo db_info = (Properties_Equation_Get_DbInfo)equa_info.get(i);
			int j = Integer.parseInt(db_info.getPL_BEI_SEQ()); //순서를 가져와서 비교
			j = j-1;
			//String symbol =  ComUtil.isNullToEmptyString(db_info.getPL_CPBI_ELE_SYMBOL());
			String part_no = ComUtil.isNullToEmptyString(db_info.getPL_CPBI_ELE_NUM());
			String chg_state = ComUtil.isNullToEmptyString(db_info.getPL_BEI_CHG_STATE());
			String ele_state = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_ELC_STATE());
			String sub_state = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_OTH_STRUC());
			String part_seq = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_SEQ());
			
			//System.out.println("symbol : " + symbol);
			//System.out.println("chg : " + chg);
			//System.out.println("elc : " + ele_state);
			//System.out.println("oth : " + sub_state);
			
			//4.1 Species : Atoms			
			Basic_Part_Info part_data = part_info.selectPartInfo(part_no);			
			String part_sym = part_data.getPL_CPBI_ELE_SYMBOL(); //기호
			String part_type = part_data.getPL_CPBI_ELE_TYPE(); //원자 분자 타입
			String part_num = part_data.getPL_CPBI_ELE_AMNUM(); //원자 번호 (주기율)
			int part_count = Integer.parseInt(part_data.getPL_CPBI_ELE_AMCOUNT()); //원자 분자 개수
			
			//System.out.println("part_no : " + part_no);
			//System.out.println("chg_state : " + chg_state);
			
			//인치키 정보 가져오기
			Part_Inchikey_Info inchi_data = xsams_info.selectInchiKeyInfo(part_no, chg_state);
			String inchikey_value = inchi_data.getPL_CPII_INCHI_KEY();
			String ioncharge_value = inchi_data.getPL_CPII_ION_CHARGE();
			String nfri_formula = inchi_data.getPL_CPII_NFRI_FOMULA();
			String inchi_value = inchi_data.getPL_CPII_INCHI();
			String stoichimetricFormula = inchi_data.getPL_CPII_STOCHIOMETRIC(); 
			
			if("e".equalsIgnoreCase(nfri_formula)){
				inchikey_value = "electron";
			}else if("hv".equalsIgnoreCase(nfri_formula)){
				inchikey_value = "photon";
			}
			
			//인치키 값을 변수에 추가
			if(i == 0 || i == 1){
				s_reactant.add("X-DCPP-" + inchikey_value);
			}else{				
				if(!ComVar.STRING_EMPTY.equalsIgnoreCase(sub_state)){
					if(ComUtil.isStringDouble(sub_state)){//숫자 변환이 가능하면,
						int state_count = Integer.parseInt(sub_state);
						for(int k = 0; k < state_count; k++){
							s_product.add("X-DCPP-" + inchikey_value);
						}
					}					
				}else{
					s_product.add("X-DCPP-" + inchikey_value);
				}
			}
			
			
			
			/*
			* 4.1. 원자 정보 가져오기
			* <Atoms> 부분 데이터
			*/			
			if(part_count == 1){ //원자 일경우												
			
				//4.1.1 Atom
		    	Element atom = new Element("Atom"); 
			
		    	//4.1.1.1 Atom : Comments
		    	Element a_comments = new Element("Comments");    
				String ele_sub = ele_state + " " + sub_state;
		    	a_comments.setText(ele_sub);
		    	atom.setContent(a_comments);
		    	
		    	//4.1.1.1 Atom : ChemicalElement
		    	Element a_chemical_element = new Element("ChemicalElement");    
		    	
		    	//4.1.1.1.1 ChemicalElement : NewClearCharge
		    	Element a_nuclear_charge = new Element("NewClearCharge");
		    	a_nuclear_charge.setText(part_num);
		    	
		    	//4.1.1.1.2 ChemicalElement : ElementSymbol
		    	Element a_element_symbol = new Element("ElementSymbol");
		    	a_element_symbol.setText(part_sym);
		    	
		    	a_chemical_element.setContent(a_nuclear_charge);
		    	a_chemical_element.addContent(a_element_symbol);
		    	
		    	//4.1.1.2 Atom : Isotope
		    	Element a_isotope = new Element("Isotope");    
		    	
		    	//4.1.1.2.1 Isotope : Ion
		    	Element a_isotope_ion = new Element("Ion");
		    	a_isotope_ion.setAttribute("speciesID", "X-DCPP-"+inchikey_value);
		    	
		    	//4.1.1.2.1.1 Isotope : Ion : IonCharge
		    	Element a_isotope_ion_charge = new Element("IonCharge");
		    	a_isotope_ion_charge.setText(ioncharge_value);
		    	
		    	//4.1.1.2.1.2 Isotope : Ion : InChiKey
		    	Element a_isotope_ion_inchikey = new Element("InChIKey");
		    	a_isotope_ion_inchikey.setText(inchikey_value);		    	
		    	a_isotope_ion.setContent(a_isotope_ion_charge);
		    	a_isotope_ion.addContent(a_isotope_ion_inchikey);
		    	a_isotope.setContent(a_isotope_ion);
		    			    	
		    	//추가
		    	atom.setContent(a_comments);
		    	atom.addContent(a_chemical_element); 
		    	atom.addContent(a_isotope);		    		    	
		    	
				if(i == 0 || atoms_check == 0){					
					atoms.setContent(atom);
					atoms_check ++;
				}else{				
					atoms.addContent(atom);
				}				
				
				//System.out.println("atoms_check : " + atoms_check);
													
				//species = species_clone.detach();
			/*
			* 4.2. 분자 정보 가져오기
			* <Molecules> 부분 데이터
			*/
			}else{ //분자일 경우
				
				
				if(!"e".equalsIgnoreCase(nfri_formula)){
				
					//4.2.1 Molecules : Molecule
			    	Element m_molcule = new Element("Molecule");
			    	m_molcule.setAttribute("speciesID", "X-DCPP-"+inchikey_value);
					
			    	//4.2.2 Molecules : Comments //미세구조와 전자배치 표현
			    	Element m_comments = new Element("Comments");    
					String ele_sub = ele_state + " " + sub_state;
					m_comments.setText(ele_sub);					
					
					//4.2.3 Molecules : MolecularChemicalSpecies
			    	Element m_molecularChemicalSpecies = new Element("MolecularChemicalSpecies");
					
			    	//4.2.3.1 MolecularChemicalSpecies : OrdinaryStructuralFormula
			    	Element m_m_ordinaryStructuralFormula = new Element("OrdinaryStructuralFormula"); 
					
			    	//4.2.3.1.1 MolecularChemicalSpecies : OrdinaryStructuralFormula : Value
			    	Element m_m_ordinaryStructuralFormula_value = new Element("Value");    	   	
			    	m_m_ordinaryStructuralFormula_value.setText(nfri_formula);    	
			    	m_m_ordinaryStructuralFormula.setContent(m_m_ordinaryStructuralFormula_value);
			    	
			    	//4.2.3.2 MolecularChemicalSpecies : StoichimetricFormula
			    	Element m_m_stoichimetricFormula = new Element("StoichimetricFormula");
			    	m_m_stoichimetricFormula.setText(stoichimetricFormula);
			    	m_m_ordinaryStructuralFormula.addContent(m_m_stoichimetricFormula);
			    	
			    	//4.2.3.2 MolecularChemicalSpecies : InchI
			    	if(!"?".equalsIgnoreCase(inchi_value)){
				    	Element m_m_inchI = new Element("InchI");
				    	m_m_inchI.setText(inchi_value);
				    	m_m_ordinaryStructuralFormula.addContent(m_m_inchI);
			    	}
			    	
			    	//4.2.3.2 MolecularChemicalSpecies : InchIKey
			    	Element m_m_inchIKey = new Element("InchIKey");
			    	m_m_inchIKey.setText(inchikey_value);
			    	m_m_ordinaryStructuralFormula.addContent(m_m_inchIKey);
			    	m_molecularChemicalSpecies.addContent(m_m_ordinaryStructuralFormula);
			    	
			    	//추가
			    	m_molcule.addContent(m_comments);
			    	m_molcule.addContent(m_molecularChemicalSpecies);
	   			    			    			    	
					if(i == 0 || molcules_check == 0){					
						molcules.setContent(m_molcule);
						molcules_check ++;
					}else{				
						molcules.addContent(m_molcule);
					}
				
				}
				
			}

			
		}
		    	    	
		//Element species_clone = species.detach();
		if(atoms_check > 0){
			species.addContent(atoms);	
		}
		
		if(molcules_check > 0){
			species.addContent(molcules);	
		}
		
		/*
		* 4.3. Particles 정보 찍기
		* <Particle> 부분 데이터
		*/
		//4.3.1 Particles : Particle
		Element particles = new Element("Particles");
		Element p_particle_ele = new Element("Particle");
		p_particle_ele.setAttribute("speciesID", "X-DCPP-electron");
		p_particle_ele.setAttribute("name", "electron");
		
		Element p_particle_pho = new Element("Particle");
		p_particle_pho.setAttribute("speciesID", "X-DCPP-photon");
		p_particle_pho.setAttribute("name", "photon");
		
		particles.setContent(p_particle_ele);
		particles.addContent(p_particle_pho);
		
		species.addContent(particles);
		
    	/*
		* 5. Processes 정보 찍기
		* <Collisions> 부분 데이터
		*/
    	Element processes = new Element("Processes");
    	
    	//5.1 Processes : Collisions
    	Element processes_coll = new Element("Collisions");
    	
    	//System.out.println("pr_no : " + pr_no + ", exp_value : " + exp_value);
    	
    	//5.1.1 Processes : Collisions : CollisionalTransition
    	Element processes_coll_collTran = new Element("CollisionalTransition");
    	//데이터 검증 구분에 따른 표시 지정?
    	/*
    	if("ET_01".equalsIgnoreCase(exp_value)){ // Experimental Data 일경우
    		processes_collisions_collisionalTrasition.setAttribute("methodRef", "MTHE");
    	}else if("ET_02".equalsIgnoreCase(exp_value)){ // Theoretical Data 일경우
    		processes_collisions_collisionalTrasition.setAttribute("methodRef", "MTHT");
    	}else if("ET_03".equalsIgnoreCase(exp_value)){ // Collectional Data 일경우
    		processes_collisions_collisionalTrasition.setAttribute("methodRef", "MTHC");
    	}else{ // Recommended Data 일경우
    		processes_collisions_collisionalTrasition.setAttribute("methodRef", "MTHR");
    	}
    	*/
    	processes_coll_collTran.setAttribute("methodRef", "MTHE");
    	processes_coll_collTran.setAttribute("id", "P-DCPP-"+pr_no);
    	
    	//5.1.1.1 Processes : Collisions : CollisionalTransition : SourceRef
    	Element processes_coll_collTran_SouRef = new Element("SourceRef");
    	processes_coll_collTran_SouRef.setText("B-DCPP-" + artcl_no);
    	
    	processes_coll_collTran.setContent(processes_coll_collTran_SouRef);
    	
    	//5.1.1.2 Processes : Collisions : CollisionalTransition : ProcessClass
    	Element processes_coll_collTran_ProClass = new Element("ProcessClass");
    	
    	//5.1.1.2.1 Processes : Collisions : CollisionalTransition : ProcessClass : UserDefinition
    	Element processes_coll_collTran_ProClass_UserD = new Element("UserDefinition");
    	processes_coll_collTran_ProClass_UserD.setText("COLLISION CROSS SECTION");    	
    	processes_coll_collTran_ProClass.setContent(processes_coll_collTran_ProClass_UserD);    	
    	processes_coll_collTran.addContent(processes_coll_collTran_ProClass);
    	
    	/*
    	* 입사, 표적입자 순서 및 생성 입자에 따른 처리.
    	*/
    	//5.1.1.3 Processes : Collisions : CollisionalTransition : Reactant // 1)입사입자, 2)표적입자    	
    	//5.1.1.3.1 Processes : Collisions : CollisionalTransition : Reactant : SpeciesRef
    	
    	//System.out.println("s_reactant.size() : " + s_reactant.size());
    	//System.out.println("s_product.size() : " + s_product.size());
    	for(int i = 0 ; i < s_reactant.size(); i++){
   			Element processes_coll_collTran_Reactant = new Element("Reactant");
   			Element processes_coll_collTran_Reactant_SpecRef = new Element("SpeciesRef");
   			processes_coll_collTran_Reactant_SpecRef.setText(s_reactant.get(i)); 
   			processes_coll_collTran_Reactant.addContent(processes_coll_collTran_Reactant_SpecRef);    		
   			processes_coll_collTran.addContent(processes_coll_collTran_Reactant);
    		//System.out.println("count : " + i);
    	}   	
    	
    	//5.1.1.4 Processes : Collisions : CollisionalTransition : Product // 1)입사입자, 2)표적입자    	
    	//5.1.1.4.1 Processes : Collisions : CollisionalTransition : Product : SpeciesRef    	
    	
		for(int i = 0 ; i < s_product.size(); i++){
			Element processes_coll_collTran_Product = new Element("Product");
			Element processes_coll_collTran_Product_SpecRef = new Element("SpeciesRef");
			processes_coll_collTran_Product_SpecRef.setText(s_product.get(i)); 
			processes_coll_collTran_Product.addContent(processes_coll_collTran_Product_SpecRef);    		
			processes_coll_collTran.addContent(processes_coll_collTran_Product); 
    		//System.out.println("count : " + i);		
    	}    	   	
    	
		/**
		* 수치데이터 단위 가져오기
		*/
		Graph_Basic_Info basic_info = (Graph_Basic_Info)graph_info.selectViewGraphBasicInfo(pr_no);	
		String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
		//String x_cal = basic_info.getPL_BGI_X_AX_CAL();
		String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
		//String y_cal = basic_info.getPL_BGI_Y_AX_CAL();
		//String y_comm = basic_info.getPL_BGI_Y_AX_COMM();
		
		/**
		* 수치데이터 가져오기
		*/
		Hashtable<String, String> gt_info = graph_info.selectGraphDataInXSAMS(pr_no);
		String x_value = (String)gt_info.get("x");
		String y_value = (String)gt_info.get("y");
		//int v_dt_count = dt_info.size();
		
    	/**
    	/* 데이터 수치값 추가
    	*/
    	//5.1.1.4 Processes : Collisions : CollisionalTransition : DataSets // 수치값 표현 
    	Element processes_coll_collTran_DataSets = new Element("DataSets");
    	Element processes_coll_collTran_DataSets_DataSet = new Element("DataSet");
    	processes_coll_collTran_DataSets_DataSet.setAttribute("dataDescription", "COLLISION CROSS SECTION");
    	
    	Element processes_coll_collTran_DataSets_DataSet_TabulatedData = new Element("TabulatedData");
    	Element processes_coll_collTran_DataSets_DataSet_TabulatedData_X = new Element("X");
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_X.setAttribute("units", x_unit);
    	Element processes_coll_collTran_DataSets_DataSet_TabulatedData_X_DataList = new Element("DataList");
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_X_DataList.setText(x_value);    	
    	
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_X.setContent(processes_coll_collTran_DataSets_DataSet_TabulatedData_X_DataList);
    	
    	Element processes_coll_collTran_DataSets_DataSet_TabulatedData_Y = new Element("Y");
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_Y.setAttribute("units", y_unit);
    	Element processes_coll_collTran_DataSets_DataSet_TabulatedData_Y_DataList = new Element("DataList");
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_Y_DataList.setText(y_value);
    	
    	processes_coll_collTran_DataSets_DataSet_TabulatedData_Y.setContent(processes_coll_collTran_DataSets_DataSet_TabulatedData_Y_DataList);
    	
    	
    	processes_coll_collTran_DataSets_DataSet_TabulatedData.setContent(processes_coll_collTran_DataSets_DataSet_TabulatedData_X);
    	processes_coll_collTran_DataSets_DataSet_TabulatedData.addContent(processes_coll_collTran_DataSets_DataSet_TabulatedData_Y);
    	
    	processes_coll_collTran_DataSets_DataSet.setContent(processes_coll_collTran_DataSets_DataSet_TabulatedData);
    	processes_coll_collTran_DataSets.setContent(processes_coll_collTran_DataSets_DataSet);
    	
    	processes_coll_collTran.addContent(processes_coll_collTran_DataSets);
    	
    	processes_coll.setContent(processes_coll_collTran);
    	processes.setContent(processes_coll);    	
    	
    	//루트 엘리먼트에 자식 엘리먼트(Child Element) 추가  	
    	
    	xsams_root.addContent(methods);
    	xsams_root.addContent(species);
    	xsams_root.addContent(processes);
    	
    	doc.setContent(xsams_root);
    	
    	ServletContext context = getServletContext();	
    	//Web Application 저장경로  지정
    	//String realFolder = "";
    	//Web Application 절대경로
    	String contextpath = context.getRealPath("/");
    	//파일이 업로드 되는 경로 지정
    	//String uploadFolder = "data_file";		
    	//인코딩 타입 지정
    	//String encType ="euc-kr";
    	//최대 업로드될 파일 크기 지정 = 10MB
    	//int maxSize = 10*1024*1024;
    	
    	String saveFolder = contextpath + "xsams_file/";
    	
    	System.out.println("saveFolder : " + saveFolder);
		//String savePath = saveFolder+"/"+filename;
    	
		int n = 1000000000 + (int) (Math.random() * 1000000000);
		//System.out.println("n : " + n);
		file_name = Integer.toString(n);
		
		String file_full_path = saveFolder +file_name+".xml";
    	//File출력부분
    	FileWriter writer = new FileWriter(file_full_path); 
    	//FileWriter writer = new FileWriter("C:\\"+file_name+".xml"); 
    	
    	XMLOutputter xo = new XMLOutputter();
    	
    	//예쁜 모양을 위해 Format.getPrettyFormat()을 지정    	
    	xo.setFormat(Format.getPrettyFormat());
    	//Format fm = xo.getFormat();    	   	
    	xo.output(doc, writer);
    	writer.close();
    	
    	//String xml = new XMLOutputter().ouptutString(doc);
    	//System.out.println("save 2 !! " + file_full_path);
        	
		//파일 다운로드를 위한 변수
		file_path = saveFolder;
		//String file_name = "test";
		//String file_ext = "";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>XSAMS Document Download</title>
</head>
 <body onLoad = 'downloadfile()'>
<form name="form1" action="" method="POST" >
<div id="content">
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_path" value="<%=file_path%>"/>
</div>
</form>
<script language = javascript>
//function downloadfile(name){ //XSAMS 파일 다운로드
function downloadfile(){
	var form2 = document.form1;
	//form에 값을 넘기기 위하여 폼객체에 페이지의 객체값을 넣는다.
	//form2.file_path.value = file_path;
	//alert(file_name);
	//form2.file_name.value = file_name;
	form2.target = "_self";
	form2.action = "pr_xsams_download_file.jsp";
	form2.submit();	
	//self.close();
	//var url = "pr_xsams_download_file.jsp?file_name="+file_name+"?file_path="+file_path;
	//var popup = window.open(url, "hiddenframe", "width=0, height=0, top=0, statusbar=no, scrollbars=no, toolbar=no");
	//popup.focus();
}

//window.onload = function(){	
//	downloadfile(file_path, file_name);
//}
</script>

</body>
</html>