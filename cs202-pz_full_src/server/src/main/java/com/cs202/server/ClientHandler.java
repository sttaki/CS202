package com.cs202.server;

import com.cs202.server.dao.*;
import com.cs202.server.model.*;
import com.cs202.server.protocol.Request;
import com.cs202.server.protocol.Response;
import com.cs202.server.service.TournamentService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final GameDao gameDao = new GameDao();
    private final ArenaDao arenaDao = new ArenaDao();
    private final TeamDao teamDao = new TeamDao();
    private final PlayerDao playerDao = new PlayerDao();
    private final TournamentDao tournamentDao = new TournamentDao();
    private final MatchDao matchDao = new MatchDao();
    private final TournamentService service = new TournamentService(matchDao, teamDao);

    public ClientHandler(Socket s){ this.socket = s; }

    @Override public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            while (true) {
                Object obj = in.readObject();
                if (!(obj instanceof Request req)) break;
                Response resp = handle(req);
                out.writeObject(resp);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handle(Request r) {
        try {
            return switch (r.getEntity()) {
                case "GAME" -> switchCrudGame(r);
                case "ARENA" -> switchCrudArena(r);
                case "TEAM" -> switchCrudTeam(r);
                case "PLAYER" -> switchCrudPlayer(r);
                case "TOURNAMENT" -> switchCrudTournament(r);
                case "MATCH" -> switchCrudMatch(r);
                case "SERVICE" -> switchService(r);
                default -> Response.error("Nepoznat entitet: " + r.getEntity());
            };
        } catch (Exception ex) {
            return Response.error(ex.getMessage());
        }
    }

    private Response switchCrudGame(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(gameDao.findAll());
            case "CREATE" -> Response.ok(gameDao.insert(mapGame(r)));
            case "UPDATE" -> { gameDao.update(mapGame(r)); yield Response.ok(null); }
            case "DELETE" -> { gameDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Game mapGame(Request r) {
        Map<String,Object> d = r.getData();
        return new Game((int)d.getOrDefault("id", 0), (String)d.get("name"));
        }

    private Response switchCrudArena(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(arenaDao.findAll());
            case "CREATE" -> Response.ok(arenaDao.insert(mapArena(r)));
            case "UPDATE" -> { arenaDao.update(mapArena(r)); yield Response.ok(null); }
            case "DELETE" -> { arenaDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Arena mapArena(Request r) {
        Map<String,Object> d = r.getData();
        return new Arena((int)d.getOrDefault("id", 0), (String)d.get("name"), (String)d.get("city"));
    }

    private Response switchCrudTeam(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(teamDao.findAll());
            case "CREATE" -> Response.ok(teamDao.insert(mapTeam(r)));
            case "UPDATE" -> { teamDao.update(mapTeam(r)); yield Response.ok(null); }
            case "DELETE" -> { teamDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Team mapTeam(Request r) {
        Map<String,Object> d = r.getData();
        Object gid = d.get("gameId");
        Integer gameId = (gid == null) ? null : ((Number)gid).intValue();
        return new Team((int)d.getOrDefault("id", 0), (String)d.get("name"), gameId);
    }

    private Response switchCrudPlayer(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(playerDao.findAll());
            case "CREATE" -> Response.ok(playerDao.insert(mapPlayer(r)));
            case "UPDATE" -> { playerDao.update(mapPlayer(r)); yield Response.ok(null); }
            case "DELETE" -> { playerDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Player mapPlayer(Request r) {
        Map<String,Object> d = r.getData();
        Object tid = d.get("teamId");
        Integer teamId = (tid == null) ? null : ((Number)tid).intValue();
        return new Player((int)d.getOrDefault("id", 0), (String)d.get("nickname"), teamId);
    }

    private Response switchCrudTournament(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(tournamentDao.findAll());
            case "CREATE" -> Response.ok(tournamentDao.insert(mapTournament(r)));
            case "UPDATE" -> { tournamentDao.update(mapTournament(r)); yield Response.ok(null); }
            case "DELETE" -> { tournamentDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Tournament mapTournament(Request r) {
        Map<String,Object> d = r.getData();
        java.time.LocalDate date = null;
        Object dv = d.get("startDate");
        if (dv instanceof String s && !s.isBlank()) date = java.time.LocalDate.parse(s);
        return new Tournament((int)d.getOrDefault("id", 0), (String)d.get("name"), date);
    }

    private Response switchCrudMatch(Request r) throws Exception {
        return switch (r.getAction()) {
            case "LIST" -> Response.ok(matchDao.findAll());
            case "CREATE" -> Response.ok(matchDao.insert(mapMatch(r)));
            case "UPDATE" -> { matchDao.update(mapMatch(r)); yield Response.ok(null); }
            case "DELETE" -> { matchDao.delete((int) r.getData().get("id")); yield Response.ok(null); }
            default -> Response.error("Akcija?");
        };
    }
    private Match mapMatch(Request r) {
        Map<String,Object> d = r.getData();
        Integer arenaId = d.get("arenaId") == null ? null : ((Number)d.get("arenaId")).intValue();
        java.time.LocalDate date = null;
        Object dv = d.get("matchDate");
        if (dv instanceof String s && !s.isBlank()) date = java.time.LocalDate.parse(s);
        return new Match(
                (int)d.getOrDefault("id", 0),
                ((Number)d.get("tournamentId")).intValue(),
                ((Number)d.get("homeTeamId")).intValue(),
                ((Number)d.get("awayTeamId")).intValue(),
                arenaId,
                date,
                ((Number)d.getOrDefault("homeScore", 0)).intValue(),
                ((Number)d.getOrDefault("awayScore", 0)).intValue(),
                (String)d.getOrDefault("status", "SCHEDULED")
        );
    }

    private Response switchService(Request r) throws Exception {
        Map<String,Object> d = r.getData();
        return switch (r.getAction()) {
            case "SCHEDULE" -> {
                int tid = ((Number)d.get("tournamentId")).intValue();
                @SuppressWarnings("unchecked") List<Integer> teamIds = (List<Integer>) d.get("teamIds");
                yield Response.ok(service.generateRoundRobin(tid, teamIds));
            }
            case "RANKING" -> {
                int tid = ((Number)d.get("tournamentId")).intValue();
                yield Response.ok(service.rankingForTournament(tid));
            }
            default -> Response.error("Nepoznata servis akcija");
        };
    }
}
