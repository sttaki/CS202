package com.cs202.server.service;

import com.cs202.server.dao.MatchDao;
import com.cs202.server.dao.TeamDao;
import com.cs202.server.model.Match;
import com.cs202.server.model.Team;

import java.util.*;

public class TournamentService {
    private final MatchDao matchDao;
    private final TeamDao teamDao;

    public TournamentService(MatchDao m, TeamDao t){
        this.matchDao = m;
        this.teamDao = t;
    }

    public List<Match> generateRoundRobin(int tournamentId, List<Integer> teamIds) throws Exception {
        List<Integer> teams = new ArrayList<>(teamIds);
        if (teams.size() < 2) return Collections.emptyList();
        if (teams.size() % 2 == 1) teams.add(-1);
        int n = teams.size(), rounds = n - 1, half = n / 2;

        List<Match> created = new ArrayList<>();
        for (int r = 0; r < rounds; r++) {
            for (int i = 0; i < half; i++) {
                int home = teams.get(i);
                int away = teams.get(n - 1 - i);
                if (home != -1 && away != -1) {
                    Match m = new Match(0, tournamentId, home, away, null, null, 0, 0, "SCHEDULED");
                    created.add(matchDao.insert(m));
                }
            }
            // rotate except first element
            List<Integer> rotated = new ArrayList<>();
            rotated.add(teams.get(0));
            rotated.add(teams.get(n - 1));
            for (int k = 1; k < n - 1; k++) rotated.add(teams.get(k));
            teams = rotated;
        }
        return created;
    }

    public List<Map<String,Object>> rankingForTournament(int tournamentId) throws Exception {
        Map<Integer, int[]> stats = new HashMap<>();
        for (Team t : teamDao.findAll()) stats.put(t.getId(), new int[]{0,0,0}); // wins, losses, points

        for (Match m : matchDao.findAll()) {
            if (m.getTournamentId() != tournamentId || !"FINISHED".equalsIgnoreCase(m.getStatus())) continue;
            int hs = m.getHomeScore(), as = m.getAwayScore();
            if (hs > as) {
                stats.get(m.getHomeTeamId())[0]++; // win
                stats.get(m.getAwayTeamId())[1]++; // loss
                stats.get(m.getHomeTeamId())[2] += 3;
            } else if (as > hs) {
                stats.get(m.getAwayTeamId())[0]++;
                stats.get(m.getHomeTeamId())[1]++;
                stats.get(m.getAwayTeamId())[2] += 3;
            } else {
                stats.get(m.getHomeTeamId())[2] += 1;
                stats.get(m.getAwayTeamId())[2] += 1;
            }
        }

        List<Map<String,Object>> table = new ArrayList<>();
        for (Map.Entry<Integer,int[]> e : stats.entrySet()) {
            Map<String,Object> row = new HashMap<>();
            row.put("teamId", e.getKey());
            row.put("wins",   e.getValue()[0]);
            row.put("losses", e.getValue()[1]);
            row.put("points", e.getValue()[2]);
            table.add(row);
        }
        table.sort((a,b) -> Integer.compare((int)b.get("points"), (int)a.get("points")));
        return table;
    }
}
