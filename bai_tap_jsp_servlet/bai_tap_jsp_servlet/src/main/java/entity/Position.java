package entity;

public class Position {

		private int positionId;
		private String Position;
		
		public Position() {}
		
		
		public Position(int positionId, String position) {
			super();
			this.positionId = positionId;
			Position = position;
		}
		public int getPositionId() {
			return positionId;
		}
		public void setPositionId(int positionId) {
			this.positionId = positionId;
			
			if (this.positionId ==1) {
				this.Position = "boss";
			}else {
				this.Position = "employee";
			}
		}
		
		public String getPosition() {
			return Position;
		}
		public void setPosition(String position) {
			this.Position = position;
			
			if (position.equals("boss")) {
				this.positionId = 1;
			}else {
				this.positionId = 2;
			}
		}
		
		@Override
		public String toString() {
			return "Position [positionId=" + positionId + ", Position=" + Position + "]";
		}
		
		
}
