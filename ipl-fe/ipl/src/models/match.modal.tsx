export interface Match{
    id: number;
    city: string;
    matchDate: string;
    manOfTheMatch: string;
    venue: string;
    firstTeam: string;
    secondTeam: string;
    tossWinner: string;
    tossDecision: string;
    winnerTeam: string;
    result: string;
    resultMargin: number;
    eliminator: boolean
    winningMethod: string;
    firstUmpire: string;
    secondUmpire: string;
}

export class MatchModal implements Match{
    id: number;
    city: string;
    matchDate: string;
    manOfTheMatch: string;
    venue: string;
    firstTeam: string;
    secondTeam: string;
    tossWinner: string;
    tossDecision: string;
    winnerTeam: string;
    result: string;
    resultMargin: number;
    eliminator: boolean;
    winningMethod: string;
    firstUmpire: string;
    secondUmpire: string;

}