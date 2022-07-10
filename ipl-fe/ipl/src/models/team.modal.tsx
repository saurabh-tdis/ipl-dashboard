import {Match} from "./match.modal";

export interface Team{
    id: number;
    teamName: string;
    totalMatches: number;
    totalWins: number;
    latestMatches: Match[];
}

export class TeamModal implements Team{
    id: number;
    teamName: string;
    totalMatches: number;
    totalWins: number;
    latestMatches: Match[]=[];

}