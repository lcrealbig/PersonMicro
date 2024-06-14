import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Person } from '../model/person';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  postPersonUrl: string =  'http://localhost:8080/api/persons'
  posCheckIdUrl: string =  'http://localhost:8080/api/persons/checkid'


  constructor(private http: HttpClient) { }

  createPerson(person: Person) {
    return this.http.post<Person>(this.postPersonUrl, person).pipe(
      map(res => {
        return res;
      })
    )
  }
}
