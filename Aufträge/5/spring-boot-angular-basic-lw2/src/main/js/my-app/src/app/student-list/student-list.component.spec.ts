import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';

import { StudentListComponent } from './student-list.component';
import { StudentService } from '../service/student.service';
import { Student } from '../model/student';

describe('StudentListComponent', () => {
  let component: StudentListComponent;
  let fixture: ComponentFixture<StudentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentListComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ StudentService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with empty students array', () => {
    expect(component.students).toEqual([]);
  });

  it('should load students on init', () => {
    const studentService = TestBed.inject(StudentService);
    const mockStudents: Student[] = [
      { id: '1', name: 'John Doe', email: 'john@example.com' },
      { id: '2', name: 'Jane Smith', email: 'jane@example.com' }
    ];
    spyOn(studentService, 'findAll').and.returnValue(of(mockStudents));

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual(mockStudents);
  });

  it('should handle server error when loading students', () => {
    const studentService = TestBed.inject(StudentService);
    spyOn(studentService, 'findAll').and.returnValue(throwError({ status: 500, message: 'Server error' }));
    spyOn(console, 'error');

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual([]);
  });

  it('should handle network error when loading students', () => {
    const studentService = TestBed.inject(StudentService);
    spyOn(studentService, 'findAll').and.returnValue(throwError({ status: 0, message: 'Network error' }));

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual([]);
  });

  it('should handle large dataset correctly', () => {
    const studentService = TestBed.inject(StudentService);
    const largeDataset: Student[] = Array.from({ length: 1000 }, (_, i) => ({
      id: `${i + 1}`,
      name: `Student ${i + 1}`,
      email: `student${i + 1}@example.com`
    }));
    spyOn(studentService, 'findAll').and.returnValue(of(largeDataset));

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual(largeDataset);
    expect(component.students.length).toBe(1000);
  });

  it('should handle students with special characters in names', () => {
    const studentService = TestBed.inject(StudentService);
    const specialCharStudents: Student[] = [
      { id: '1', name: 'José María-González', email: 'jose@example.com' },
      { id: '2', name: 'Пётр Иванов', email: 'petr@example.com' },
      { id: '3', name: '王小明', email: 'wang@example.com' }
    ];
    spyOn(studentService, 'findAll').and.returnValue(of(specialCharStudents));

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual(specialCharStudents);
  });

  it('should handle unauthorized access error', () => {
    const studentService = TestBed.inject(StudentService);
    spyOn(studentService, 'findAll').and.returnValue(throwError({ status: 401, message: 'Unauthorized' }));

    component.ngOnInit();

    expect(studentService.findAll).toHaveBeenCalled();
    expect(component.students).toEqual([]);
  });

  it('should maintain students array reference during error', () => {
    const studentService = TestBed.inject(StudentService);
    const originalArray = component.students;
    spyOn(studentService, 'findAll').and.returnValue(throwError({ status: 500, message: 'Server error' }));

    component.ngOnInit();

    expect(component.students).toBe(originalArray);
  });
});
