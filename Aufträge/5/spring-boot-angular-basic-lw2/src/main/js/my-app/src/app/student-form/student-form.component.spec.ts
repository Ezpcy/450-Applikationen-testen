import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';

import { StudentFormComponent } from './student-form.component';
import { StudentService } from '../service/student.service';
import { Student } from '../model/student';

describe('StudentFormComponent', () => {
  let component: StudentFormComponent;
  let fixture: ComponentFixture<StudentFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentFormComponent ],
      imports: [ RouterTestingModule, HttpClientTestingModule, FormsModule ],
      providers: [ StudentService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with empty student', () => {
    expect(component.student).toBeDefined();
    expect(component.student.name).toBe('');
    expect(component.student.email).toBe('');
  });

  it('should navigate to student list on gotoStudentList', () => {
    const router = TestBed.inject(Router);
    spyOn(router, 'navigate');

    component.gotoStudentList();

    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });

  it('should save student and navigate on submit', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    spyOn(studentService, 'save').and.returnValue(of(new Student()));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });

  it('should handle form submission with valid data', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    const savedStudent = { id: '1', name: 'John Doe', email: 'john@example.com' };

    component.student.name = 'John Doe';
    component.student.email = 'john@example.com';

    spyOn(studentService, 'save').and.returnValue(of(savedStudent));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(component.student.name).toBe('John Doe');
    expect(component.student.email).toBe('john@example.com');
    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });

  it('should handle service error during submission', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    spyOn(studentService, 'save').and.returnValue(throwError({ status: 500, message: 'Server error' }));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should handle validation error during submission', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    spyOn(studentService, 'save').and.returnValue(throwError({ status: 400, message: 'Validation error' }));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should submit form with empty fields', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    spyOn(studentService, 'save').and.returnValue(of(new Student()));
    spyOn(router, 'navigate');

    expect(component.student.name).toBe('');
    expect(component.student.email).toBe('');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });

  it('should submit form with special characters in name', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    const savedStudent = { id: '1', name: 'José María-González', email: 'jose@example.com' };

    component.student.name = 'José María-González';
    component.student.email = 'jose@example.com';

    spyOn(studentService, 'save').and.returnValue(of(savedStudent));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });

  it('should submit form with very long name', () => {
    const studentService = TestBed.inject(StudentService);
    const router = TestBed.inject(Router);
    const longName = 'A'.repeat(255);
    const savedStudent = { id: '1', name: longName, email: 'test@example.com' };

    component.student.name = longName;
    component.student.email = 'test@example.com';

    spyOn(studentService, 'save').and.returnValue(of(savedStudent));
    spyOn(router, 'navigate');

    component.onSubmit();

    expect(studentService.save).toHaveBeenCalledWith(component.student);
    expect(router.navigate).toHaveBeenCalledWith(['/students']);
  });
});
