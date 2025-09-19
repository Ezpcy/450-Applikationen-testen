import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StudentService } from './student.service';
import { Student } from '../model/student';

describe('StudentService', () => {
  let service: StudentService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [StudentService]
    });
    service = TestBed.inject(StudentService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('findAll', () => {
    it('should return an Observable<Student[]>', () => {
      const dummyStudents: Student[] = [
        { id: '1', name: 'John Doe', email: 'john@example.com' },
        { id: '2', name: 'Jane Smith', email: 'jane@example.com' }
      ];

      service.findAll().subscribe(students => {
        expect(students.length).toBe(2);
        expect(students).toEqual(dummyStudents);
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      expect(req.request.method).toBe('GET');
      req.flush(dummyStudents);
    });

    it('should handle empty response', () => {
      const emptyStudents: Student[] = [];

      service.findAll().subscribe(students => {
        expect(students).toEqual(emptyStudents);
        expect(students.length).toBe(0);
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      expect(req.request.method).toBe('GET');
      req.flush(emptyStudents);
    });

    it('should handle HTTP error', () => {
      service.findAll().subscribe({
        next: () => fail('Expected an error, not students'),
        error: error => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Server Error');
        }
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      req.flush('Something went wrong', { status: 500, statusText: 'Server Error' });
    });
  });

  describe('save', () => {
    it('should post student and return Observable<Student>', () => {
      const newStudent: Student = { id: '', name: 'John Doe', email: 'john@example.com' };
      const savedStudent: Student = { id: '1', name: 'John Doe', email: 'john@example.com' };

      service.save(newStudent).subscribe(student => {
        expect(student).toEqual(savedStudent);
        expect(student.id).toBe('1');
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(newStudent);
      req.flush(savedStudent);
    });

    it('should handle save with existing student (update)', () => {
      const existingStudent: Student = { id: '1', name: 'John Doe Updated', email: 'john.updated@example.com' };

      service.save(existingStudent).subscribe(student => {
        expect(student).toEqual(existingStudent);
        expect(student.name).toBe('John Doe Updated');
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(existingStudent);
      req.flush(existingStudent);
    });

    it('should handle validation error on save', () => {
      const invalidStudent: Student = { id: '', name: '', email: 'invalid-email' };

      service.save(invalidStudent).subscribe({
        next: () => fail('Expected an error, not success'),
        error: error => {
          expect(error.status).toBe(400);
          expect(error.statusText).toBe('Bad Request');
        }
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      req.flush('Validation failed', { status: 400, statusText: 'Bad Request' });
    });

    it('should handle server error on save', () => {
      const student: Student = { id: '', name: 'John Doe', email: 'john@example.com' };

      service.save(student).subscribe({
        next: () => fail('Expected an error, not success'),
        error: error => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Internal Server Error');
        }
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      req.flush('Server error', { status: 500, statusText: 'Internal Server Error' });
    });
  });

  describe('integration scenarios', () => {
    it('should handle network timeout', () => {
      const student: Student = { id: '', name: 'John Doe', email: 'john@example.com' };

      service.save(student).subscribe({
        next: () => fail('Expected an error, not success'),
        error: error => {
          expect(error.name).toBe('TimeoutError');
        }
      });

      const req = httpMock.expectOne('http://localhost:8081/students');
      req.error(new ProgressEvent('timeout'), { status: 0, statusText: 'Timeout' });
    });
  });
});