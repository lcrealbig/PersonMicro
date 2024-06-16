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


  title = 'frontend';
  person!: Person;
  addPersonForm: any;
  
  constructor(private formBuilder: FormBuilder, private personService: PersonService, private personValidation: PersonValidationService, private notification: NotificationService){

    this.addPersonForm = this.formBuilder.group({
      id: ['',[Validators.required, Validators.minLength(5)]],
      name: ['',[Validators.required, Validators.minLength(2)]],
      birthday:['',[Validators.required, Validators.minLength(5)]],
    })
  }

  onSubmit() {
    const id = this.addPersonForm.get('id').value;
    const name = this.addPersonForm.get('name').value;
    const birthday = this.addPersonForm.get('birthday').value;
  
    const pers = {
      id: id,
      name: name,
      birthday: birthday
    };
  
    this.personService.createPerson(pers).subscribe({
      next: (response) => {
        console.log(' resp',response);
        this.notification.showNotification(`Success: ${response.name} has been successfully added to database!`, 'Close', 'success-snackbar');
      },
      error: (error) => {
        if (error.status === 400){
          this.notification.showNotification(`Wrong data sent to a server. Error http status is: ${error.status}  `, 'Close', 'error-snackbar');

        }
        this.notification.showNotification(`Failure: ${error.status}  `, 'Close', 'error-snackbar');
        console.error('Error registering person:' , error);
      }
    });
  }

  isIdLengthValid(): boolean{
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdLengthValid(id);
  }

  isIdPatternValid(): boolean {
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdPatternValid(id);
  }

  isNameNotEmpty(): boolean {
    const name = this.addPersonForm.get('name').value;
    return this.personValidation.isNameNotEmpty(name);
  }

  isValidDateFormat(): boolean {
    const birthday = this.addPersonForm.get('birthday').value;
    return this.personValidation.isValidDateFormat(birthday);
  }
}


