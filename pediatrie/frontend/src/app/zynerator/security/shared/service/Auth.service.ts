import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

import {environment} from 'src/environments/environment';

import {BehaviorSubject} from 'rxjs';


import {TokenService} from './Token.service';
import {UserDto} from '../model/User.model';
import {RoleDto} from '../model/Role.model';
import {RoleUserDto} from '../model/RoleUser.model';
import {MessageService} from 'primeng/api';
import {UserService} from "./User.service";
import {MedecinAdminService} from "../../../../shared/service/admin/commun/MedecinAdmin.service";
import {MedecinDto} from "../../../../shared/model/commun/Medecin.model";
import {InfermierAdminService} from "../../../../shared/service/admin/commun/InfermierAdmin.service";
import {InfermierDto} from "../../../../shared/model/commun/Infermier.model";
import {InfermierInfermierService} from "../../../../shared/service/infermier/commun/InfermierInfermier.service";
import {MedecinMedecinService} from "../../../../shared/service/medecin/commun/MedecinMedecin.service";


@Injectable({
    providedIn: 'root'
})
export class AuthService {

    readonly API = environment.loginUrl;
    public _user = new UserDto();
    private _authenticatedUser = new UserDto();
    private _authenticatedInfermier:InfermierDto = new InfermierDto();
    private _authenticatedMedecin:MedecinDto = new MedecinDto();
    private _authenticated = (JSON.parse(localStorage.getItem('autenticated')) as boolean) || false;
    public _loggedIn = new BehaviorSubject<boolean>(false);
    public loggedIn$ = this._loggedIn.asObservable();
    public error: string = null;




    constructor( private userService: UserService, private http: HttpClient, private tokenService: TokenService, private router: Router, private messageService: MessageService,private infermierService:InfermierInfermierService,private medecinService:MedecinMedecinService) {

    }



    public login(username: string, password: string) {
        this.http.post<any>(this.API + 'login', {username, password}, {observe: 'response'}).subscribe(
            resp  => {
                this.error = null;
                const jwt = 'Bearer '+resp.body.accessToken;
                jwt != null ? this.tokenService.saveToken(jwt) : false;
                if(resp.body.roles[0]== 'ROLE_ADMIN'){
                    this.userService.findById(resp.body.id).subscribe(
                        (user: UserDto) => {
                            console.log('USer FirstName : '+user.firstName)
                            this._authenticatedUser = user;
                            localStorage.setItem("authenticatedUser",JSON.stringify(this._authenticatedUser));
                            console.log(this._authenticatedUser)
                        },
                        (error: any) => {
                            console.error('Error occurred while fetching user:', error);
                        }
                    );
                }else if(resp.body.roles[0] == 'ROLE_INFERMIER'){
                    this.infermierService.findById(resp.body.id).subscribe(
                        ( infermier:InfermierDto)=>{
                            this._authenticatedInfermier = infermier;
                            localStorage.setItem("authenticatedInfermier",JSON.stringify(this.authenticatedInfermier));
                            console.log(this._authenticatedInfermier);
                        }
                    )
                }else if(resp.body.roles[0] == 'ROLE_MEDECIN'){
                    this.medecinService.findById(resp.body.id).subscribe(
                        ( medecin:MedecinDto)=>{
                            this._authenticatedMedecin = medecin;
                            localStorage.setItem("authenticatedMedecin",JSON.stringify(this.authenticatedMedecin));
                            console.log(this._authenticatedMedecin);
                        }
                    )
                }

                this.loadInfos();
                console.log('you are logged in successfully');
                if(resp.body.roles[0]== 'ROLE_INFERMIER'){
                    this.router.navigate(['/' + environment.rootAppUrl + '/infermier']);
                }else if (resp.body.roles[0]== 'ROLE_MEDECIN'){
                    this.router.navigate(['/' + environment.rootAppUrl + '/medecin'])
                }
                else{
                    this.router.navigate(['/' + environment.rootAppUrl + '/admin']);
                }

            }, (error: HttpErrorResponse) => {
                console.log("error")
                this.error = error.error.message;
                if (error.status === 401) {
                    let errorMessage = '';
                    if (this.error && error.message) {
                        errorMessage = error.error.message;
                    } else {
                        errorMessage = 'Unauthorized: Invalid credentials';
                    }
                    if (errorMessage.toLowerCase().includes('user is disabled')) {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Error ' + error.status,
                            detail: 'Unauthorized: User is disabled'
                        });
                    } else {
                        this.messageService.add({severity: 'error', summary: 'Error ' + error.status, detail: errorMessage});
                    }
                }else if (error.status === 405) {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error ' + error.status,
                        detail: 'Method Not Allowed: Please check your request method'
                    });
                } else {
                    this.messageService.add({severity: 'error', summary: 'Error ' + error.status, detail: 'An unexpected error occurred'});
                }
            }

        );

    }

    public loadInfos() {
        const tokenDecoded = this.tokenService.decode();
        const username = tokenDecoded.sub;
        const roles = tokenDecoded.roles;
        const email = tokenDecoded.email;
        const firstName = tokenDecoded.firstName;
        const lastName = tokenDecoded.lastName;
        const phone = tokenDecoded.phone;
        const passwordChanged = tokenDecoded.passwordChanged;
        this._authenticatedUser.passwordChanged = passwordChanged;
        this._authenticatedUser.username = username;
        this._authenticatedUser.phone = phone;
        this._authenticatedUser.firstName = firstName;
        this._authenticatedUser.lastName = lastName;
        this._authenticatedUser.email = email;
        roles.forEach(role => {
            const roleUser = new RoleUserDto();
            roleUser.role.authority = role;
            this._authenticatedUser.roleUsers = [];
            this._authenticatedUser.roleUsers.push(roleUser);
        });
        localStorage.setItem('autenticated', JSON.stringify(true));
        this.authenticated = true;
        this._loggedIn.next(true);

    }
    public hasRole(role: RoleDto): boolean {
        const index = this._authenticatedUser.roleUsers.findIndex(r => r.role.authority === role.authority);
        return index > -1 ? true : false;
    }

    public registerAdmin() {
        console.log(this.user)
        this.http.post<any>(this.API + 'api/user/', this.user, {observe: 'response'}).subscribe(
            resp => {
                this.router.navigate(['/login']);
            }, (error: HttpErrorResponse) => {
                console.log(error.error);
            }
        );
    }

    public logout() {
        this.tokenService.removeToken();
        localStorage.setItem('autenticated', JSON.stringify(false));
        this.authenticated = false;
        this._loggedIn.next(false);
        this._authenticatedUser = new UserDto();
        this.router.navigate(['']);
    }

    get user(): UserDto {
        return this._user;
    }

    set user(value: UserDto) {
        this._user = value;
    }

    get authenticated(): boolean {

        return this._authenticated;
    }

    set authenticated(value: boolean) {
        this._authenticated = value;
    }

    get authenticatedUser(): UserDto {
        return this._authenticatedUser;
    }

    set authenticatedUser(value: UserDto) {
        this._authenticatedUser = value;
    }

    get item(): UserDto {
        return this.userService.item;
    }

    set item(value: UserDto) {
        this.userService.item = value;
    }

    get authenticatedInfermier(): InfermierDto {
        return this._authenticatedInfermier;
    }

    set authenticatedInfermier(value: InfermierDto) {
        this._authenticatedInfermier = value;
    }

    get authenticatedMedecin(): MedecinDto {
        return this._authenticatedMedecin;
    }

    set authenticatedMedecin(value: MedecinDto) {
        this._authenticatedMedecin = value;
    }

}
