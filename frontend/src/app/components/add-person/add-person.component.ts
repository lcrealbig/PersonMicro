import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Person } from 'src/app/model/person';
import { NotificationService } from 'src/app/services/notificationService/notification.service';
import { PersonService } from 'src/app/services/personService/person.service';
import { PersonValidationService } from 'src/app/services/validationService/person-validation.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {

  person!: Person;
  addPersonForm: any;
  isIdUnique: boolean = false;
  idStatusText: string = '';

  constructor(private formBuilder: FormBuilder,
              private personService: PersonService,
              private personValidation: PersonValidationService,
              private notification: NotificationService) {
    this.addPersonForm = this.formBuilder.group({
      id: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(5)]],
      name: ['',[Validators.required, Validators.minLength(2)]],
      birthday:['',[Validators.required,Validators.maxLength(10), Validators.minLength(10)]],
    });
  }

 async onSubmit() {
    const id = this.addPersonForm.get('id').value;
    const name = this.addPersonForm.get('name').value;
    const birthday = this.addPersonForm.get('birthday').value;
    const person = {
      id: id,
      name: name,
      birthday: birthday
    };
  
    this.personService.createPerson(person).subscribe({
      next: (response) => {
        this.notification.showNotification(`Success: ${response.name} has been successfully added to database!`, 'Close', 'success-snackbar');
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

  isIdPatternValid(): boolean {
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdPatternValid(id);
  }

  isNameNotEmpty(): boolean {
    const name = this.addPersonForm.get('name').value;
    return this.personValidation.isFieldNotEmpty(name);
  }

  isIdNotEmpty(): boolean {
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isFieldNotEmpty(id);
  }

  isValidDateFormat(): boolean {
    const birthday = this.addPersonForm.get('birthday').value;
    return this.personValidation.isValidDateFormat(birthday);
  }

  isPersonIdUnique() {
    const id = this.addPersonForm.get('id').value;
     this.personValidation.isIdUnique(id).subscribe({
      next: (response) => {
        if (response === true){
          this.idStatusText = " Id is UNIQUE";
        } else if (response === false){
          this.idStatusText = " Id is TAKEN";
        }
      },
      error: (error) => {
        if (error.status === 400) {
          this.notification.showNotification(`Wrong data sent to a server. Error http status is: ${error.status}  `, 'Close', 'error-snackbar');
        } else if (error.status >= 500) {
          this.notification.showNotification(`Failure, Server could not proceed the request: ${error.status}  `, 'Close', 'error-snackbar');
        }
        this.idStatusText = 'Error while checking id'
        this.notification.showNotification(`Failure, Server could not proceed the request: ${error.status}  `, 'Close', 'error-snackbar');
      }
    });
  }

 setIdStatus(): string {
  this.isPersonIdUnique();
    if (this.isIdUnique) {
      return this.idStatusText = 'this id is unique.'
    } else if (!this.isIdUnique) { 
      return this.idStatusText = 'this id is Taken.'
    }
    return this.idStatusText;
  }
}
