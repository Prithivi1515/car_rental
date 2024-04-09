import { NgModule } from "@angular/core";
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzTimePickerModule } from 'ng-zorro-antd/time-picker';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';

@NgModule({
    exports: [
        NzSpinModule,
        NzButtonModule,
        NzInputModule,
        NzFormModule,
        NzLayoutModule,
        NzSelectModule,
        NzTimePickerModule,
        NzDatePickerModule
    ]
})

export class NgZorroImportsModule {}