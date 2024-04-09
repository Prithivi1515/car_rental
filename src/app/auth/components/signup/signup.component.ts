import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  isSpinning:boolean  = false;
  signupForm! : FormGroup;

  constructor(private fb: FormBuilder, private authService:AuthService, private message:NzMessageService, private router:Router) {}


  ngOnInit() {
    this.signupForm = this.fb.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required , this.confirmationValidate]],
      phone: [null, [Validators.required]],
    });
  }
  confirmationValidate = (control: FormControl) => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.signupForm.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return {};
  }

  register(){
    console.log(this.signupForm.value);
    this.authService.register(this.signupForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id != null){
        this.message.success('User registered successfully', {nzDuration: 5000});
        this.router.navigate(['/login']);
      }
      else{
        this.message.error('User registration failed' , {nzDuration: 5000});
      }
    });
  }

}
