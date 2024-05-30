import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {MedecinDto} from "../../../model/commun/Medecin.model";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../../../zynerator/security/shared/service/Auth.service";
import {UserDto} from "../../../../zynerator/security/shared/model/User.model";
import {UserService} from "../../../../zynerator/security/shared/service/User.service";

@Injectable({
    providedIn: 'root'
})
export class ProfilAdminService {
    constructor(private http:HttpClient,private authService:AuthService) {
    }
    public edit(): Observable<UserDto> {
        return this.http.put<UserDto>('http://localhost:8036/api/user/', this.authenticatedUser);
    }
    get authenticatedUser(): UserDto {
        return this.authService.authenticatedUser;
    }
    set authenticatedUser(value: UserDto) {
        this.authService.authenticatedUser = value;
    }
    public  editPassword(username:string,password:string){
        console.log(password)
        return this.http.post(  'http://localhost:8036/api/user/changePassword',
            {username, password},
            {observe: 'response'});
    }

}
