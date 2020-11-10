package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Institute;
import com.lti.entity.Ministry;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.repository.NspRepository;

@Service
public class NspServiceImpl implements NspService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private NspRepository nspRepo;

	@Override
	public void addAScheme(Scheme scheme) {

		nspRepo.saveAScheme(scheme);

	}

	@Override
	public Scheme findAScheme(long schemeId) {

		return nspRepo.findAScheme(schemeId);
	}

	@Override
	public long registerAnInstitute(Institute institute) {
		if (!nspRepo.isInstitutePresent(institute.getInstituteEmail())) {
			institute.setInstituteNodalOfficerApproval("Not Approved");
			institute.setInstituteMinistryApproval("Not Approved");
			long id = nspRepo.saveAnInstitute(institute);
			String text = "Successfully registered. Your id is " + id + "\n You Will be notified after Verification.";
			String subject = "Registration Confirmation";
			emailService.sendEmailForNewRegistration(institute.getInstituteEmail(), text, subject);
			return id;

		} else {
			throw new NspServiceException("Already exists");
		}
	}

	@Override
	public Institute getAnInstituteById(long instituteId) {
		return nspRepo.findAnInstituteById(instituteId);
	}

	@Override
	public List<Institute> fetchAllInstitutes() {
		return nspRepo.fetchAllInstitutes();
	}

	@Override
	public Institute instituteLogin(long instituteId, String password) {
//		if(!nspRepo.isInstitutePresent(userId)) {
//			return null;
//		}
//		else {
//			return nspRepo.findInstituteByIdAndPassword(userId, password);
//		}
		Institute ins = nspRepo.findAnInstituteById(instituteId);
		if (ins.getInstitutePassword().equals(password) && ins.getInstituteStatus().equals("Approved")) {
			return ins;
		} else {
			throw new NspServiceException("Log in Id is not activated yet");
		}

	}

	@Override
	public void instituteUpdatesAStudentStatus(Student student, String status) {
		student.setStudentStatus(status);
		nspRepo.saveAStudent(student);
	}

	@Override
	public void instituteUpdatesAForm(ScholarshipForm form, String status) {

		if (status.equals("Rejected")) {
			form.setInstituteVerificationStatus(status);
			nodalUpdatesAForm(form, status);
		}

		else {
			form.setInstituteVerificationStatus(status);
			nspRepo.saveAScholarshipForm(form);
		}

	}

	@Override
	public void ministryUpdatesAnInstituteStatus(Institute institute, String status) {
		institute.setInstituteMinistryApproval(status);
		institute.setInstituteStatus(status);
		String text = "Your login " + institute.getInstituteId() + " is now activated.";
		String subject = "Registration Confirmation";
		emailService.sendEmailForNewRegistration(institute.getInstituteEmail(), text, subject);
		nspRepo.saveAnInstitute(institute);

	}

	@Override
	public List<Institute> fetchAllInstitutesByMinistryStatus(String status) {
		List<Institute> institutes = nspRepo.fetchAllInstitutes();
		List<Institute> ins = new ArrayList<>();
		for (Institute institute : institutes) {
			if (institute.getInstituteMinistryApproval().equals(status)
					&& institute.getInstituteNodalOfficerApproval().equals("Approved")) {
				ins.add(institute);
			}
		}
		return ins;
	}

	@Override
	public void registerAStudent(Student student) {
		// TODO Auto-generated method stub
		if (!nspRepo.isStudentPresent(student.getStudentEmail())) {
			student.setStudentStatus("Not Approved");
			long id = nspRepo.saveAStudent(student);

			String text = "Successfully registered. Your id is " + id;
			String subject = "Registration Confirmation";
			emailService.sendEmailForNewRegistration(student.getStudentEmail(), text, subject);
		} else {
			throw new NspServiceException("Student already registered");
		}

	}

	@Override
	public Student getAStudentById(long studentId) {
		// TODO Auto-generated method stub
		return nspRepo.findAStudentById(studentId);
	}

	@Override
	public Student studentLogin(long aadhar, String password) {
//		if(!nspRepo.isStudentPresent(aadhar)) {
//			return null;
//		}
//		else {
//			return nspRepo.findStudentByIdAndPassword(aadhar, password);
//		}

		Student student = nspRepo.findAStudentById(aadhar);

		if (student.getStudentPassword().equals(password)) {
			return student;
		} else {
			throw new NspServiceException("Invalid credentials");
		}

	}

	@Override
	public List<Student> fetchAllStudents() {

		return nspRepo.fetchAllStudents();
	}

	@Override
	public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status) {

		Institute ins = nspRepo.findAnInstituteById(instituteId);
//		
		List<Student> st = ins.getStudents();

		
		List<Student> st2 = new ArrayList<>();

        for(Student s: st) {
            if(s.getStudentStatus().equals(status)) {
                st2.add(s);
            }
        }


        return st2;
	}

	@Override
	public void registerANodal(Nodal nodal) {
		if (!nspRepo.isNodalPresent(nodal.getNodalUid())) {

			nspRepo.saveANodal(nodal);
			System.out.println("Nodal successfully registered");
		} else {
			System.out.println("Registration failed");
		}

	}

	@Override
	public Nodal getANodalById(int nodalUid) {

		return nspRepo.findANodalById(nodalUid);
	}

	@Override
	public Nodal nodalLogin(int userId, String password) {
		/*
		 * if(nspRepo.isNodalPresent(userId)) { return null; } else { return
		 * nspRepo.findNodalByIdAndPassword(userId, password); }
		 */

		Nodal nodal = nspRepo.findANodalById(userId);
		if (nodal.getNodalPassword().equals(password)) {
			return nodal;
		} else {
			throw new NspServiceException("Login failed");
		}
	}

	@Override
	public void nodalUpdatesAForm(ScholarshipForm form, String formStatus) {
		if (formStatus.equals("Rejected")) {
			form.setNodalVerificationStatus(formStatus);

			ministryUpdatesAFormStatus(form, formStatus);

//			form.setMinistryVerificationStatus(formStatus);
//			form.setStatus(formStatus);
//			form.setDateOfApproval(LocalDate.now());
		} else {
			form.setNodalVerificationStatus(formStatus);
			nspRepo.saveAScholarshipForm(form);
		}

	}

	@Override
	public void nodalUpdatesAnInstituteStatus(Institute institute, String status) {
		institute.setInstituteNodalOfficerApproval(status);
		nspRepo.saveAnInstitute(institute);

	}

	@Override
	public void applyAScholarshipForm(ScholarshipForm form) {
		/*
		 * List<ScholarshipForm> forms=nspRepo.fetchAllScholarshipForms();
		 * 
		 * for (ScholarshipForm scholarshipForm : forms) {
		 * if(scholarshipForm.getAadharNumber()==form.getAadharNumber()) { throw new
		 * NspServiceException("You have already applied for this scheme"); }
		 *
		 * }
		 */
		if (nspRepo.hasStudentAppliedForAForm(form.getStudent().getStudentAadharNumber())) {
			throw new NspServiceException("You have already applied for a scheme");
		} else {

			form.setInstituteVerificationStatus("Not Approved");
			form.setNodalVerificationStatus("Not Approved");
			form.setMinistryVerificationStatus("Not Approved");
			form.setStatus("Not Approved");

			long id = nspRepo.saveAScholarshipForm(form);

			String text = "Successfully registered. Your form id is " + id;
			String subject = "Form Successfully applied";
			emailService.sendEmailForNewRegistration(form.getStudent().getStudentEmail(), text, subject);

		}

	}

	@Override
	public void updateAScholarshipForm(ScholarshipForm form) {
		nspRepo.saveAScholarshipForm(form);
	}

	@Override
	public ScholarshipForm getAScholarshipFormById(long form_id) {

		return nspRepo.findAScholarshipFormById(form_id);
	}

	@Override
	public List<ScholarshipForm> fetchAllScholarshipForms() {

		return nspRepo.fetchAllScholarshipForms();
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByInstituteStatus(String status) {
		List<ScholarshipForm> forms = nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> sf = new ArrayList<>();
		for (ScholarshipForm scholarshipForm : forms) {
			if (scholarshipForm.getInstituteVerificationStatus().equals(status)) {
				sf.add(scholarshipForm);
			}
		}
		return sf;
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByNodalStatus(String status) {
		List<ScholarshipForm> forms = nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> sf = new ArrayList<>();
		for (ScholarshipForm scholarshipForm : forms) {
			if (scholarshipForm.getNodalVerificationStatus().equals(status)) {
				sf.add(scholarshipForm);
			}
		}
		return sf;
	}

	@Override
	public List<ScholarshipForm> fetchFormsOfParticularInstituteByInstituteStatus(long instituteId, String status) {
		/*
		 * List<ScholarshipForm> students=nspRepo.fetchAllScholarshipForms();
		 * List<ScholarshipForm> st=new ArrayList<>();
		 * 
		 * for (ScholarshipForm student : students) {
		 * if(student.getInstituteVerificationStatus().equals(status) &&
		 * (student.getInstituteObj().getInstituteId()==instituteId)) { //added null
		 * else getting infinite loop //student.setInstitute(null); st.add(student); } }
		 */
		return nspRepo.fetchFormsOfAnInstituteWithInstituteStatus(instituteId, status);
	}

	@Override
	public boolean ministryLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ministryUpdatesAFormStatus(ScholarshipForm form, String status) {
		form.setMinistryVerificationStatus(status);
		form.setStatus(status);
		form.setDateOfApproval(LocalDate.now());
		nspRepo.saveAScholarshipForm(form);
		long id = form.getFormId();
		String text = "Your scholarship form with ID: " + id + " is " + status;
		String subject = "Your form is approved";
		emailService.sendEmailForNewRegistration(form.getStudent().getStudentEmail(), text, subject);

	}

	@Override
	public List<Institute> fetchAllInstitutesByNodalStatus(String status) {
		List<Institute> institutes = nspRepo.fetchAllInstitutes();
		List<Institute> ins = new ArrayList<>();
		for (Institute institute : institutes) {
			if (institute.getInstituteNodalOfficerApproval().equals(status)) {
				ins.add(institute);
			}
		}
		return ins;
	}

	@Override
	public List<Institute> fetchAllInstitutesByStatus(String status) {
		List<Institute> institutes = nspRepo.fetchAllInstitutes();
		List<Institute> ins = new ArrayList<>();
		for (Institute institute : institutes) {
			if (institute.getInstituteStatus().equals(status)) {
				ins.add(institute);
			}
		}
		return ins;
	}

	@Override
	public List<ScholarshipForm> fetchFormsUsingNodalStatus(String status) {

		List<ScholarshipForm> list = nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> forms = new ArrayList<>();
		for (ScholarshipForm sc : list) {
			if (sc.getInstituteVerificationStatus().equals("Approved")
					&& sc.getNodalVerificationStatus().equals(status)) {
				forms.add(sc);
			}
		}
		// return nspRepo.fetchFormsUsingNodalStatus(status);
		return forms;
	}

	@Override
	public List<ScholarshipForm> fetchFormsUsingMinistryStatus(String status) {
		// return nspRepo.fetchFormsUsingMinistryStatus(status);
		List<ScholarshipForm> list = nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> forms = new ArrayList<>();
		for (ScholarshipForm sc : list) {
			if (sc.getNodalVerificationStatus().equals("Not Approved")
					&& sc.getMinistryVerificationStatus().equals(status)) {
				forms.add(sc);
			}
		}
		// return nspRepo.fetchFormsUsingNodalStatus(status);
		return forms;
	}

	@Override
	public Long findInstituteOfStudent(long studentId) {
		return nspRepo.findInstituteOfStudent(studentId);
	}

	@Override
	public String studentForgotPassword(long studentId, String email) {
		Student st=nspRepo.findAStudentById(studentId);
		if(st.getStudentEmail().equals(email)) {
			String text = "You password is "+st.getStudentPassword();
			String subject = "Password for given aadhar";
			emailService.sendEmailForNewRegistration(email, text, subject);
			return st.getStudentPassword();

		}
		else {
			throw new NspServiceException("Invalid crediantials");
		}
		
	}

	@Override
    public String instituteForgotPassword(long instituteId, String email) {
        Institute ins=nspRepo.findAnInstituteById(instituteId);
        if(ins.getInstituteEmail().equals(email)) {
            String text="Your password is "+ins.getInstitutePassword();
            String subject="Password for your institute login";
            emailService.sendEmailForNewRegistration(email, text, subject);
            return ins.getInstitutePassword();
        }
        else {
            throw new NspServiceException("Invalid credentials");
        }
    }

	@Override
	public long findFormByStudentId(long studentId) {
		
		return nspRepo.findAStudentById(studentId).getForm().getFormId();
		
	}

	@Override
	public long fetchFormByStudentId(long studentId) {
		return nspRepo.fetchFormByStudentId(studentId);
	}

	@Override
	public String nodalForgotPassword(int nodalId, String email) {
		Nodal nd=nspRepo.findANodalById(nodalId);
		if(nd.getNodalEmail().equals(email)) {
			
			String text = "You password is "+nd.getNodalPassword();
			String subject = "Password for given nodal";
			emailService.sendEmailForNewRegistration(email, text, subject);
			return nd.getNodalPassword();

		}
		else {
			throw new NspServiceException("Invalid crediantials");
		}
		
	}

	@Override
	public String ministryForgotPassword(String ministryId, String email) {
		String ID = "Admin";
		String password = "AdminPassword";
		String Email = "admin@nsp.com";
		
		if(ministryId.equals(ID) && Email.equals(email)) {
			String text = "Your Password is: " + password;
			String subject = "Password Recovery";
			emailService.sendEmailForNewRegistration(email, text, subject);
			return password;
		}
		else {
			throw new NspServiceException("Invalid ID");
		}
	}

}
