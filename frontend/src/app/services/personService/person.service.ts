import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Person } from '../../model/person';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  commonPersonOperationUrl: string =  'http://localhost:8080/api/persons'
  posCheckIdUrl: string =  'http://localhost:8080/api/persons/checkid/{personId}'


  constructor(private http: HttpClient) { }

  createPerson(person: Person) {
    return this.http.post<Person>(this.commonPersonOperationUrl, person).pipe(
      map(res => {
        return res;
      })
    )
  }

  isPersonIdUnique(personId: string) {
    return this.http.get<Boolean>(this.posCheckIdUrl.replace('{personId}', personId)).pipe(
      map(res => {
        return res;
      })
    )
  }

  getAllPersons() {
    return this.http.get<Person[]>(this.commonPersonOperationUrl).pipe(
      map(res => {
        return res;
      })
    )
  }
}
