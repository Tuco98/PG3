package com.lti.repository;

import java.util.List;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;

public class NspRepositoryImpl implements NspRepository {

	@Override
	public void saveAScheme(Scheme scheme) {
		// TODO Auto-generated method stub

	}

	@Override
	public Scheme findAScheme(long schemeUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scheme> fetchAllSchemes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveAnInstitute(Institute institute) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Institute findAnInstituteById(long instituteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institute findAnInstituteByInstituteCode(String instituteCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Institute> fetchAllInstitutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean instituteLogin(long userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long saveAStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student findAStudentById(long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean studentLogin(long aadhar, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> fetchAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveANodal(Nodal nodal) {
		// TODO Auto-generated method stub

	}

	@Override
	public Nodal findANodalById(int nodalUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nodalLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long saveAScholarshipForm(ScholarshipForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ScholarshipForm findAScholarshipFormById(long form_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchAllScholarshipForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ministryLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
