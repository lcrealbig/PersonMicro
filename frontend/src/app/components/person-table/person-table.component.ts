import { Component, OnInit } from '@angular/core';
import { Person } from 'src/app/model/person';
import { NotificationService } from 'src/app/services/notificationService/notification.service';
import { PersonService } from 'src/app/services/personService/person.service';

@Component({
  selector: 'app-person-table',
  templateUrl: './person-table.component.html',
  styleUrls: ['./person-table.component.css']
})
export class PersonTableComponent implements OnInit {

  constructor(private personService: PersonService, private notification: NotificationService){
  }
  ngOnInit(): void {
    this.getPersons();
  }

  persons: Person[] = [];

  getPersons() {
    return this.personService.getAllPersons().subscribe({
      next: (response) => {        
      this.persons = response;
      },
      error: (error) => {
        if (error.status === 400) {
          this.notification.showNotification(`Wrong data sent to a server; Id is already taken. Error http status is: ${error.status}  `, 'Close', 'error-snackbar');

        } else if (error.status >= 500) {
          this.notification.showNotification(`Failure, Server could not proceed the request: ${error.status}  `, 'Close', 'error-snackbar');
        }
      }
    });
  }

}
