import TeamInfo from "../components/TeamInfo";
import PlayerListTable from "../components/tables/PlayerListTable";
import NavBar from "../components/NavBar";

export default function Home() {
  return (
    <>
      <NavBar />
      <div className="mt-12">
        <TeamInfo />
      </div>
      <div className="mt-24">
        <PlayerListTable />
      </div>
    </>
  );
}
