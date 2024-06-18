import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Person } from '../../model/person';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  personsUrl: string = 'http://localhost:8080/api/persons';
  postCheckIdUrl: string = 'http://localhost:8080/api/person/checkid/{personId}';
  personUrl: string = 'http://localhost:8080/api/person';

  constructor(private http: HttpClient) {
  }

  createPerson(person: Person) {
    return this.http.post<Person>(this.personUrl, person).pipe(
      map(res => {
        return res;
      })
    );
  }

  isPersonIdUnique(personId: string) {
    return this.http.get<Boolean>(this.postCheckIdUrl.replace('{personId}', personId)).pipe(
      map(res => {
        return res;
      })
    );
  }

  getAllPersons() {
    return this.http.get<Person[]>(this.personsUrl).pipe(
      map(res => {
        return res;
      })
    );
  }
}
